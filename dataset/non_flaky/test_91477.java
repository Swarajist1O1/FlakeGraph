class DummyClass_91477 {
    @TestLogging("_root:DEBUG, org.elasticsearch.cluster.routing.allocation:TRACE, org.elasticsearch.cluster.action.shard:TRACE," +
    public void testPrimaryReplicaResyncFailed() throws Exception {
        String master = internalCluster().startMasterOnlyNode(Settings.EMPTY);
        final int numberOfReplicas = between(2, 3);
        final String oldPrimary = internalCluster().startDataOnlyNode();
        assertAcked(
            prepareCreate("test", Settings.builder().put(indexSettings())
                .put(SETTING_NUMBER_OF_SHARDS, 1)
                .put(SETTING_NUMBER_OF_REPLICAS, numberOfReplicas)));
        final ShardId shardId = new ShardId(clusterService().state().metaData().index("test").getIndex(), 0);
        final Set<String> replicaNodes = new HashSet<>(internalCluster().startDataOnlyNodes(numberOfReplicas));
        ensureGreen();
        assertAcked(
            client(master).admin().cluster().prepareUpdateSettings()
                .setTransientSettings(Settings.builder().put("cluster.routing.allocation.enable", "none")).get());
        logger.info("--> Indexing with gap in seqno to ensure that some operations will be replayed in resync");
        long numDocs = scaledRandomIntBetween(5, 50);
        for (int i = 0; i < numDocs; i++) {
            IndexResponse indexResult = index("test", "doc", Long.toString(i));
            assertThat(indexResult.getShardInfo().getSuccessful(), equalTo(numberOfReplicas + 1));
        }
        final IndexShard oldPrimaryShard = internalCluster().getInstance(IndicesService.class, oldPrimary).getShardOrNull(shardId);
        EngineTestCase.generateNewSeqNo(IndexShardTestCase.getEngine(oldPrimaryShard)); // Make gap in seqno.
        long moreDocs = scaledRandomIntBetween(1, 10);
        for (int i = 0; i < moreDocs; i++) {
            IndexResponse indexResult = index("test", "doc", Long.toString(numDocs + i));
            assertThat(indexResult.getShardInfo().getSuccessful(), equalTo(numberOfReplicas + 1));
        }
        final Set<String> replicasSide1 = Sets.newHashSet(randomSubsetOf(between(1, numberOfReplicas - 1), replicaNodes));
        final Set<String> replicasSide2 = Sets.difference(replicaNodes, replicasSide1);
        NetworkDisruption partition = new NetworkDisruption(new TwoPartitions(replicasSide1, replicasSide2), new NetworkDisconnect());
        internalCluster().setDisruptionScheme(partition);
        logger.info("--> isolating some replicas during primary-replica resync");
        partition.startDisrupting();
        internalCluster().stopRandomNode(InternalTestCluster.nameFilter(oldPrimary));
        // Checks that we fails replicas in one side but not mark them as stale.
        assertBusy(() -> {
            ClusterState state = client(master).admin().cluster().prepareState().get().getState();
            final IndexShardRoutingTable shardRoutingTable = state.routingTable().shardRoutingTable(shardId);
            final String newPrimaryNode = state.getRoutingNodes().node(shardRoutingTable.primary.currentNodeId()).node().getName();
            assertThat(newPrimaryNode, not(equalTo(oldPrimary)));
            Set<String> selectedPartition = replicasSide1.contains(newPrimaryNode) ? replicasSide1 : replicasSide2;
            assertThat(shardRoutingTable.activeShards(), hasSize(selectedPartition.size()));
            for (ShardRouting activeShard : shardRoutingTable.activeShards()) {
                assertThat(state.getRoutingNodes().node(activeShard.currentNodeId()).node().getName(), isIn(selectedPartition));
            }
            assertThat(state.metaData().index("test").inSyncAllocationIds(shardId.id()), hasSize(numberOfReplicas + 1));
        }, 1, TimeUnit.MINUTES);
        assertAcked(
            client(master).admin().cluster().prepareUpdateSettings()
                .setTransientSettings(Settings.builder().put("cluster.routing.allocation.enable", "all")).get());
        partition.stopDisrupting();
        partition.ensureHealthy(internalCluster());
        logger.info("--> stop disrupting network and re-enable allocation");
        assertBusy(() -> {
            ClusterState state = client(master).admin().cluster().prepareState().get().getState();
            assertThat(state.routingTable().shardRoutingTable(shardId).activeShards(), hasSize(numberOfReplicas));
            assertThat(state.metaData().index("test").inSyncAllocationIds(shardId.id()), hasSize(numberOfReplicas + 1));
            for (String node : replicaNodes) {
                IndexShard shard = internalCluster().getInstance(IndicesService.class, node).getShardOrNull(shardId);
                assertThat(shard.getLocalCheckpoint(), equalTo(numDocs + moreDocs));
            }
        }, 30, TimeUnit.SECONDS);
        internalCluster().assertConsistentHistoryBetweenTranslogAndLuceneIndex();
    }

}