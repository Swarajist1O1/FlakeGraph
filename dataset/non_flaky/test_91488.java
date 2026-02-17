class DummyClass_91488 {
    @TestLogging(
    public void testRerouteRecovery() throws Exception {
        logger.info("--> start node A");
        final String nodeA = internalCluster().startNode();

        logger.info("--> create index on node: {}", nodeA);
        ByteSizeValue shardSize = createAndPopulateIndex(INDEX_NAME, 1, SHARD_COUNT, REPLICA_COUNT).getShards()[0].getStats().getStore().size();

        logger.info("--> start node B");
        final String nodeB = internalCluster().startNode();

        ensureGreen();

        logger.info("--> slowing down recoveries");
        slowDownRecovery(shardSize);

        logger.info("--> move shard from: {} to: {}", nodeA, nodeB);
        client().admin().cluster().prepareReroute()
                .add(new MoveAllocationCommand(INDEX_NAME, 0, nodeA, nodeB))
                .execute().actionGet().getState();

        logger.info("--> waiting for recovery to start both on source and target");
        final Index index = resolveIndex(INDEX_NAME);
        assertBusy(() -> {
            IndicesService indicesService = internalCluster().getInstance(IndicesService.class, nodeA);
            assertThat(indicesService.indexServiceSafe(index).getShard(0).recoveryStats().currentAsSource(),
                    equalTo(1));
            indicesService = internalCluster().getInstance(IndicesService.class, nodeB);
            assertThat(indicesService.indexServiceSafe(index).getShard(0).recoveryStats().currentAsTarget(),
                    equalTo(1));
        });

        logger.info("--> request recoveries");
        RecoveryResponse response = client().admin().indices().prepareRecoveries(INDEX_NAME).execute().actionGet();

        List<RecoveryState> recoveryStates = response.shardRecoveryStates().get(INDEX_NAME);
        List<RecoveryState> nodeARecoveryStates = findRecoveriesForTargetNode(nodeA, recoveryStates);
        assertThat(nodeARecoveryStates.size(), equalTo(1));
        List<RecoveryState> nodeBRecoveryStates = findRecoveriesForTargetNode(nodeB, recoveryStates);
        assertThat(nodeBRecoveryStates.size(), equalTo(1));

        assertRecoveryState(nodeARecoveryStates.get(0), 0, RecoverySource.EmptyStoreRecoverySource.INSTANCE, true, Stage.DONE, null, nodeA);
        validateIndexRecoveryState(nodeARecoveryStates.get(0).getIndex());

        assertOnGoingRecoveryState(nodeBRecoveryStates.get(0), 0, PeerRecoverySource.INSTANCE, true, nodeA, nodeB);
        validateIndexRecoveryState(nodeBRecoveryStates.get(0).getIndex());

        logger.info("--> request node recovery stats");
        NodesStatsResponse statsResponse = client().admin().cluster().prepareNodesStats().clear().setIndices(new CommonStatsFlags(CommonStatsFlags.Flag.Recovery)).get();
        long nodeAThrottling = Long.MAX_VALUE;
        long nodeBThrottling = Long.MAX_VALUE;
        for (NodeStats nodeStats : statsResponse.getNodes()) {
            final RecoveryStats recoveryStats = nodeStats.getIndices().getRecoveryStats();
            if (nodeStats.getNode().getName().equals(nodeA)) {
                assertThat("node A should have ongoing recovery as source", recoveryStats.currentAsSource(), equalTo(1));
                assertThat("node A should not have ongoing recovery as target", recoveryStats.currentAsTarget(), equalTo(0));
                nodeAThrottling = recoveryStats.throttleTime().millis();
            }
            if (nodeStats.getNode().getName().equals(nodeB)) {
                assertThat("node B should not have ongoing recovery as source", recoveryStats.currentAsSource(), equalTo(0));
                assertThat("node B should have ongoing recovery as target", recoveryStats.currentAsTarget(), equalTo(1));
                nodeBThrottling = recoveryStats.throttleTime().millis();
            }
        }

        logger.info("--> checking throttling increases");
        final long finalNodeAThrottling = nodeAThrottling;
        final long finalNodeBThrottling = nodeBThrottling;
        assertBusy(() -> {
            NodesStatsResponse statsResponse1 = client().admin().cluster().prepareNodesStats().clear().setIndices(new CommonStatsFlags(CommonStatsFlags.Flag.Recovery)).get();
            assertThat(statsResponse1.getNodes(), hasSize(2));
            for (NodeStats nodeStats : statsResponse1.getNodes()) {
                final RecoveryStats recoveryStats = nodeStats.getIndices().getRecoveryStats();
                if (nodeStats.getNode().getName().equals(nodeA)) {
                    assertThat("node A throttling should increase", recoveryStats.throttleTime().millis(), greaterThan(finalNodeAThrottling));
                }
                if (nodeStats.getNode().getName().equals(nodeB)) {
                    assertThat("node B throttling should increase", recoveryStats.throttleTime().millis(), greaterThan(finalNodeBThrottling));
                }
            }
        });


        logger.info("--> speeding up recoveries");
        restoreRecoverySpeed();

        // wait for it to be finished
        ensureGreen();

        response = client().admin().indices().prepareRecoveries(INDEX_NAME).execute().actionGet();

        recoveryStates = response.shardRecoveryStates().get(INDEX_NAME);
        assertThat(recoveryStates.size(), equalTo(1));

        assertRecoveryState(recoveryStates.get(0), 0, PeerRecoverySource.INSTANCE, true, Stage.DONE, nodeA, nodeB);
        validateIndexRecoveryState(recoveryStates.get(0).getIndex());
        Consumer<String> assertNodeHasThrottleTimeAndNoRecoveries = nodeName ->  {
            NodesStatsResponse nodesStatsResponse = client().admin().cluster().prepareNodesStats().setNodesIds(nodeName)
                .clear().setIndices(new CommonStatsFlags(CommonStatsFlags.Flag.Recovery)).get();
            assertThat(nodesStatsResponse.getNodes(), hasSize(1));
            NodeStats nodeStats = nodesStatsResponse.getNodes().get(0);
            final RecoveryStats recoveryStats = nodeStats.getIndices().getRecoveryStats();
            assertThat(recoveryStats.currentAsSource(), equalTo(0));
            assertThat(recoveryStats.currentAsTarget(), equalTo(0));
            assertThat(nodeName + " throttling should be >0", recoveryStats.throttleTime().millis(), greaterThan(0L));
        };
        // we have to use assertBusy as recovery counters are decremented only when the last reference to the RecoveryTarget
        // is decremented, which may happen after the recovery was done.
        assertBusy(() -> assertNodeHasThrottleTimeAndNoRecoveries.accept(nodeA));
        assertBusy(() -> assertNodeHasThrottleTimeAndNoRecoveries.accept(nodeB));

        logger.info("--> bump replica count");
        client().admin().indices().prepareUpdateSettings(INDEX_NAME)
                .setSettings(Settings.builder().put("number_of_replicas", 1)).execute().actionGet();
        ensureGreen();

        assertBusy(() -> assertNodeHasThrottleTimeAndNoRecoveries.accept(nodeA));
        assertBusy(() -> assertNodeHasThrottleTimeAndNoRecoveries.accept(nodeB));

        logger.info("--> start node C");
        String nodeC = internalCluster().startNode();
        assertFalse(client().admin().cluster().prepareHealth().setWaitForNodes("3").get().isTimedOut());

        logger.info("--> slowing down recoveries");
        slowDownRecovery(shardSize);

        logger.info("--> move replica shard from: {} to: {}", nodeA, nodeC);
        client().admin().cluster().prepareReroute()
                .add(new MoveAllocationCommand(INDEX_NAME, 0, nodeA, nodeC))
                .execute().actionGet().getState();

        response = client().admin().indices().prepareRecoveries(INDEX_NAME).execute().actionGet();
        recoveryStates = response.shardRecoveryStates().get(INDEX_NAME);

        nodeARecoveryStates = findRecoveriesForTargetNode(nodeA, recoveryStates);
        assertThat(nodeARecoveryStates.size(), equalTo(1));
        nodeBRecoveryStates = findRecoveriesForTargetNode(nodeB, recoveryStates);
        assertThat(nodeBRecoveryStates.size(), equalTo(1));
        List<RecoveryState> nodeCRecoveryStates = findRecoveriesForTargetNode(nodeC, recoveryStates);
        assertThat(nodeCRecoveryStates.size(), equalTo(1));

        assertRecoveryState(nodeARecoveryStates.get(0), 0, PeerRecoverySource.INSTANCE, false, Stage.DONE, nodeB, nodeA);
        validateIndexRecoveryState(nodeARecoveryStates.get(0).getIndex());

        assertRecoveryState(nodeBRecoveryStates.get(0), 0, PeerRecoverySource.INSTANCE, true, Stage.DONE, nodeA, nodeB);
        validateIndexRecoveryState(nodeBRecoveryStates.get(0).getIndex());

        // relocations of replicas are marked as REPLICA and the source node is the node holding the primary (B)
        assertOnGoingRecoveryState(nodeCRecoveryStates.get(0), 0, PeerRecoverySource.INSTANCE, false, nodeB, nodeC);
        validateIndexRecoveryState(nodeCRecoveryStates.get(0).getIndex());

        if (randomBoolean()) {
            // shutdown node with relocation source of replica shard and check if recovery continues
            internalCluster().stopRandomNode(InternalTestCluster.nameFilter(nodeA));
            ensureStableCluster(2);

            response = client().admin().indices().prepareRecoveries(INDEX_NAME).execute().actionGet();
            recoveryStates = response.shardRecoveryStates().get(INDEX_NAME);

            nodeARecoveryStates = findRecoveriesForTargetNode(nodeA, recoveryStates);
            assertThat(nodeARecoveryStates.size(), equalTo(0));
            nodeBRecoveryStates = findRecoveriesForTargetNode(nodeB, recoveryStates);
            assertThat(nodeBRecoveryStates.size(), equalTo(1));
            nodeCRecoveryStates = findRecoveriesForTargetNode(nodeC, recoveryStates);
            assertThat(nodeCRecoveryStates.size(), equalTo(1));

            assertRecoveryState(nodeBRecoveryStates.get(0), 0, PeerRecoverySource.INSTANCE, true, Stage.DONE, nodeA, nodeB);
            validateIndexRecoveryState(nodeBRecoveryStates.get(0).getIndex());

            assertOnGoingRecoveryState(nodeCRecoveryStates.get(0), 0, PeerRecoverySource.INSTANCE, false, nodeB, nodeC);
            validateIndexRecoveryState(nodeCRecoveryStates.get(0).getIndex());
        }

        logger.info("--> speeding up recoveries");
        restoreRecoverySpeed();
        ensureGreen();

        response = client().admin().indices().prepareRecoveries(INDEX_NAME).execute().actionGet();
        recoveryStates = response.shardRecoveryStates().get(INDEX_NAME);

        nodeARecoveryStates = findRecoveriesForTargetNode(nodeA, recoveryStates);
        assertThat(nodeARecoveryStates.size(), equalTo(0));
        nodeBRecoveryStates = findRecoveriesForTargetNode(nodeB, recoveryStates);
        assertThat(nodeBRecoveryStates.size(), equalTo(1));
        nodeCRecoveryStates = findRecoveriesForTargetNode(nodeC, recoveryStates);
        assertThat(nodeCRecoveryStates.size(), equalTo(1));

        assertRecoveryState(nodeBRecoveryStates.get(0), 0, PeerRecoverySource.INSTANCE, true, Stage.DONE, nodeA, nodeB);
        validateIndexRecoveryState(nodeBRecoveryStates.get(0).getIndex());

        // relocations of replicas are marked as REPLICA and the source node is the node holding the primary (B)
        assertRecoveryState(nodeCRecoveryStates.get(0), 0, PeerRecoverySource.INSTANCE, false, Stage.DONE, nodeB, nodeC);
        validateIndexRecoveryState(nodeCRecoveryStates.get(0).getIndex());
    }

}