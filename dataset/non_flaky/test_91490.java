class DummyClass_91490 {
@TestLogging("_root:DEBUG")
    public void testAssignmentWithJustAddedNodes() throws Exception {
        internalCluster().startNode();
        final String index = "index";
        prepareCreate(index).setSettings(Settings.builder().put(IndexMetaData.SETTING_NUMBER_OF_SHARDS, 1)
            .put(IndexMetaData.SETTING_NUMBER_OF_REPLICAS, 0)).get();
        ensureGreen(index);

        // close to have some unassigned started shards shards..
        client().admin().indices().prepareClose(index).get();


        final String masterName = internalCluster().getMasterName();
        final ClusterService clusterService = internalCluster().clusterService(masterName);
        final AllocationService allocationService = internalCluster().getInstance(AllocationService.class, masterName);
        clusterService.submitStateUpdateTask("test-inject-node-and-reroute", new ClusterStateUpdateTask() {
            @Override
            public ClusterState execute(ClusterState currentState) throws Exception {
                // inject a node
                ClusterState.Builder builder = ClusterState.builder(currentState);
                builder.nodes(DiscoveryNodes.builder(currentState.nodes()).add(new DiscoveryNode("_non_existent",
                        buildNewFakeTransportAddress(), emptyMap(), emptySet(), Version.CURRENT)));

                // open index
                final IndexMetaData indexMetaData = IndexMetaData.builder(currentState.metaData().index(index)).state(IndexMetaData.State.OPEN).build();

                builder.metaData(MetaData.builder(currentState.metaData()).put(indexMetaData, true));
                builder.blocks(ClusterBlocks.builder().blocks(currentState.blocks()).removeIndexBlocks(index));
                ClusterState updatedState = builder.build();

                RoutingTable.Builder routingTable = RoutingTable.builder(updatedState.routingTable());
                routingTable.addAsRecovery(updatedState.metaData().index(index));
                updatedState = ClusterState.builder(updatedState).routingTable(routingTable.build()).build();

                return allocationService.reroute(updatedState, "reroute");

            }

}