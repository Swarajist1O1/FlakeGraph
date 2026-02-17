class DummyClass_91476 {
@TestLogging("_root:DEBUG,org.elasticsearch.cluster.service:TRACE,org.elasticsearch.discovery.zen:TRACE")
    public void testSimpleMinimumMasterNodes() throws Exception {

        Settings settings = Settings.builder()
                .put("discovery.zen.minimum_master_nodes", 2)
                .put(ZenDiscovery.PING_TIMEOUT_SETTING.getKey(), "200ms")
                .put("discovery.initial_state_timeout", "500ms")
                .build();

        logger.info("--> start first node");
        internalCluster().startNode(settings);

        logger.info("--> should be blocked, no master...");
        ClusterState state = client().admin().cluster().prepareState().setLocal(true).execute().actionGet().getState();
        assertThat(state.blocks().hasGlobalBlock(DiscoverySettings.NO_MASTER_BLOCK_ID), equalTo(true));
        assertThat(state.nodes().getSize(), equalTo(1)); // verify that we still see the local node in the cluster state

        logger.info("--> start second node, cluster should be formed");
        internalCluster().startNode(settings);

        ClusterHealthResponse clusterHealthResponse = client().admin().cluster().prepareHealth().setWaitForEvents(Priority.LANGUID).setWaitForNodes("2").execute().actionGet();
        assertThat(clusterHealthResponse.isTimedOut(), equalTo(false));

        state = client().admin().cluster().prepareState().setLocal(true).execute().actionGet().getState();
        assertThat(state.blocks().hasGlobalBlock(DiscoverySettings.NO_MASTER_BLOCK_ID), equalTo(false));
        state = client().admin().cluster().prepareState().setLocal(true).execute().actionGet().getState();
        assertThat(state.blocks().hasGlobalBlock(DiscoverySettings.NO_MASTER_BLOCK_ID), equalTo(false));

        state = client().admin().cluster().prepareState().execute().actionGet().getState();
        assertThat(state.nodes().getSize(), equalTo(2));
        assertThat(state.metaData().indices().containsKey("test"), equalTo(false));

        createIndex("test");
        NumShards numShards = getNumShards("test");
        logger.info("--> indexing some data");
        for (int i = 0; i < 100; i++) {
            client().prepareIndex("test", "type1", Integer.toString(i)).setSource("field", "value").execute().actionGet();
        }
        // make sure that all shards recovered before trying to flush
        assertThat(client().admin().cluster().prepareHealth("test").setWaitForActiveShards(numShards.totalNumShards).execute().actionGet().getActiveShards(), equalTo(numShards.totalNumShards));
        // flush for simpler debugging
        flushAndRefresh();

        logger.info("--> verify we the data back");
        for (int i = 0; i < 10; i++) {
            assertThat(client().prepareSearch().setSize(0).setQuery(QueryBuilders.matchAllQuery()).execute().actionGet().getHits().getTotalHits(), equalTo(100L));
        }

        internalCluster().stopCurrentMasterNode();
        awaitBusy(() -> {
            ClusterState clusterState = client().admin().cluster().prepareState().setLocal(true).execute().actionGet().getState();
            return clusterState.blocks().hasGlobalBlock(DiscoverySettings.NO_MASTER_BLOCK_ID);
        });
        state = client().admin().cluster().prepareState().setLocal(true).execute().actionGet().getState();
        assertThat(state.blocks().hasGlobalBlock(DiscoverySettings.NO_MASTER_BLOCK_ID), equalTo(true));
        // verify that both nodes are still in the cluster state but there is no master
        assertThat(state.nodes().getSize(), equalTo(2));
        assertThat(state.nodes().getMasterNode(), equalTo(null));

        logger.info("--> starting the previous master node again...");
        internalCluster().startNode(settings);

        clusterHealthResponse = client().admin().cluster().prepareHealth().setWaitForEvents(Priority.LANGUID).setWaitForYellowStatus().setWaitForNodes("2").execute().actionGet();
        assertThat(clusterHealthResponse.isTimedOut(), equalTo(false));

        state = client().admin().cluster().prepareState().setLocal(true).execute().actionGet().getState();
        assertThat(state.blocks().hasGlobalBlock(DiscoverySettings.NO_MASTER_BLOCK_ID), equalTo(false));
        state = client().admin().cluster().prepareState().setLocal(true).execute().actionGet().getState();
        assertThat(state.blocks().hasGlobalBlock(DiscoverySettings.NO_MASTER_BLOCK_ID), equalTo(false));

        state = client().admin().cluster().prepareState().execute().actionGet().getState();
        assertThat(state.nodes().getSize(), equalTo(2));
        assertThat(state.metaData().indices().containsKey("test"), equalTo(true));

        ensureGreen();

        logger.info("--> verify we the data back after cluster reform");
        for (int i = 0; i < 10; i++) {
            assertHitCount(client().prepareSearch().setSize(0).setQuery(QueryBuilders.matchAllQuery()).execute().actionGet(), 100);
        }

        internalCluster().stopRandomNonMasterNode();
        assertBusy(() -> {
            ClusterState state1 = client().admin().cluster().prepareState().setLocal(true).execute().actionGet().getState();
            assertThat(state1.blocks().hasGlobalBlock(DiscoverySettings.NO_MASTER_BLOCK_ID), equalTo(true));
        });

        logger.info("--> starting the previous master node again...");
        internalCluster().startNode(settings);

        ensureGreen();
        clusterHealthResponse = client().admin().cluster().prepareHealth().setWaitForEvents(Priority.LANGUID).setWaitForNodes("2").setWaitForGreenStatus().execute().actionGet();
        assertThat(clusterHealthResponse.isTimedOut(), equalTo(false));

        state = client().admin().cluster().prepareState().setLocal(true).execute().actionGet().getState();
        assertThat(state.blocks().hasGlobalBlock(DiscoverySettings.NO_MASTER_BLOCK_ID), equalTo(false));
        state = client().admin().cluster().prepareState().setLocal(true).execute().actionGet().getState();
        assertThat(state.blocks().hasGlobalBlock(DiscoverySettings.NO_MASTER_BLOCK_ID), equalTo(false));

        state = client().admin().cluster().prepareState().execute().actionGet().getState();
        assertThat(state.nodes().getSize(), equalTo(2));
        assertThat(state.metaData().indices().containsKey("test"), equalTo(true));

        logger.info("Running Cluster Health");
        ensureGreen();

        logger.info("--> verify we the data back");
        for (int i = 0; i < 10; i++) {
            assertHitCount(client().prepareSearch().setSize(0).setQuery(QueryBuilders.matchAllQuery()).execute().actionGet(), 100);
        }
    }

}