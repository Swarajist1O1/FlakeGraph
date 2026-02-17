class DummyClass_91458 {
    @TestLogging("org.elasticsearch.action.bulk:TRACE,org.elasticsearch.action.search:TRACE")
    public void testRelocationWhileRefreshing() throws Exception {
        int numberOfRelocations = scaledRandomIntBetween(1, rarely() ? 10 : 4);
        int numberOfReplicas = randomBoolean() ? 0 : 1;
        int numberOfNodes = numberOfReplicas == 0 ? 2 : 3;

        logger.info("testRelocationWhileIndexingRandom(numRelocations={}, numberOfReplicas={}, numberOfNodes={})", numberOfRelocations, numberOfReplicas, numberOfNodes);

        String[] nodes = new String[numberOfNodes];
        logger.info("--> starting [node_0] ...");
        nodes[0] = internalCluster().startNode();

        logger.info("--> creating test index ...");
        prepareCreate(
                "test",
                Settings.builder()
                        .put("index.number_of_shards", 1)
                        .put("index.number_of_replicas", numberOfReplicas)
                        .put("index.refresh_interval", -1) // we want to control refreshes
                        .put(IndexService.GLOBAL_CHECKPOINT_SYNC_INTERVAL_SETTING.getKey(), "100ms"))
                .get();

        for (int i = 1; i < numberOfNodes; i++) {
            logger.info("--> starting [node_{}] ...", i);
            nodes[i] = internalCluster().startNode();
            if (i != numberOfNodes - 1) {
                ClusterHealthResponse healthResponse = client().admin().cluster().prepareHealth().setWaitForEvents(Priority.LANGUID)
                        .setWaitForNodes(Integer.toString(i + 1)).setWaitForGreenStatus().execute().actionGet();
                assertThat(healthResponse.isTimedOut(), equalTo(false));
            }
        }

        final Semaphore postRecoveryShards = new Semaphore(0);
        final IndexEventListener listener = new IndexEventListener() {
            @Override
            public void indexShardStateChanged(IndexShard indexShard, @Nullable IndexShardState previousState, IndexShardState currentState, @Nullable String reason) {
                if (currentState == IndexShardState.POST_RECOVERY) {
                    postRecoveryShards.release();
                }
            }

}