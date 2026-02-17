class DummyClass_91467 {
    @TestLogging(
    public void testIsolateMasterAndVerifyClusterStateConsensus() throws Exception {
        final List<String> nodes = startCluster(3);

        assertAcked(prepareCreate("test")
                .setSettings(Settings.builder()
                        .put(IndexMetaData.SETTING_NUMBER_OF_SHARDS, 1 + randomInt(2))
                        .put(IndexMetaData.SETTING_NUMBER_OF_REPLICAS, randomInt(2))
                ));

        ensureGreen();
        String isolatedNode = internalCluster().getMasterName();
        TwoPartitions partitions = isolateNode(isolatedNode);
        NetworkDisruption networkDisruption = addRandomDisruptionType(partitions);
        networkDisruption.startDisrupting();

        String nonIsolatedNode = partitions.getMajoritySide().iterator().next();

        // make sure cluster reforms
        ensureStableCluster(2, nonIsolatedNode);

        // make sure isolated need picks up on things.
        assertNoMaster(isolatedNode, TimeValue.timeValueSeconds(40));

        // restore isolation
        networkDisruption.stopDisrupting();

        for (String node : nodes) {
            ensureStableCluster(3, new TimeValue(DISRUPTION_HEALING_OVERHEAD.millis() + networkDisruption.expectedTimeToHeal().millis()),
                    true, node);
        }

        logger.info("issue a reroute");
        // trigger a reroute now, instead of waiting for the background reroute of RerouteService
        assertAcked(client().admin().cluster().prepareReroute());
        // and wait for it to finish and for the cluster to stabilize
        ensureGreen("test");

        // verify all cluster states are the same
        // use assert busy to wait for cluster states to be applied (as publish_timeout has low value)
        assertBusy(() -> {
            ClusterState state = null;
            for (String node : nodes) {
                ClusterState nodeState = getNodeClusterState(node);
                if (state == null) {
                    state = nodeState;
                    continue;
                }
                // assert nodes are identical
                try {
                    assertEquals("unequal versions", state.version(), nodeState.version());
                    assertEquals("unequal node count", state.nodes().getSize(), nodeState.nodes().getSize());
                    assertEquals("different masters ", state.nodes().getMasterNodeId(), nodeState.nodes().getMasterNodeId());
                    assertEquals("different meta data version", state.metaData().version(), nodeState.metaData().version());
                    assertEquals("different routing", state.routingTable().toString(), nodeState.routingTable().toString());
                } catch (AssertionError t) {
                    fail("failed comparing cluster state: " + t.getMessage() + "\n" +
                            "--- cluster state of node [" + nodes.get(0) + "]: ---\n" + state +
                            "\n--- cluster state [" + node + "]: ---\n" + nodeState);
                }

            }
        });
    }

}