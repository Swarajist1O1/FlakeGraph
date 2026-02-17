class DummyClass_91457 {
    @TestLogging("org.elasticsearch.action.bulk:TRACE,org.elasticsearch.action.search:TRACE")
    public void testRelocationWhileIndexingRandom() throws Exception {
        int numberOfRelocations = scaledRandomIntBetween(1, rarely() ? 10 : 4);
        int numberOfReplicas = randomBoolean() ? 0 : 1;
        int numberOfNodes = numberOfReplicas == 0 ? 2 : 3;

        logger.info("testRelocationWhileIndexingRandom(numRelocations={}, numberOfReplicas={}, numberOfNodes={})", numberOfRelocations, numberOfReplicas, numberOfNodes);

        String[] nodes = new String[numberOfNodes];
        logger.info("--> starting [node1] ...");
        nodes[0] = internalCluster().startNode();

        logger.info("--> creating test index ...");
        prepareCreate("test", Settings.builder()
            .put("index.number_of_shards", 1)
            .put("index.number_of_replicas", numberOfReplicas)
        ).get();


        for (int i = 2; i <= numberOfNodes; i++) {
            logger.info("--> starting [node{}] ...", i);
            nodes[i - 1] = internalCluster().startNode();
            if (i != numberOfNodes) {
                ClusterHealthResponse healthResponse = client().admin().cluster().prepareHealth().setWaitForEvents(Priority.LANGUID)
                        .setWaitForNodes(Integer.toString(i)).setWaitForGreenStatus().execute().actionGet();
                assertThat(healthResponse.isTimedOut(), equalTo(false));
            }
        }

        int numDocs = scaledRandomIntBetween(200, 2500);
        try (BackgroundIndexer indexer = new BackgroundIndexer("test", "type1", client(), numDocs)) {
            logger.info("--> waiting for {} docs to be indexed ...", numDocs);
            waitForDocs(numDocs, indexer);
            logger.info("--> {} docs indexed", numDocs);

            logger.info("--> starting relocations...");
            int nodeShiftBased = numberOfReplicas; // if we have replicas shift those
            for (int i = 0; i < numberOfRelocations; i++) {
                int fromNode = (i % 2);
                int toNode = fromNode == 0 ? 1 : 0;
                fromNode += nodeShiftBased;
                toNode += nodeShiftBased;
                numDocs = scaledRandomIntBetween(200, 1000);
                logger.debug("--> Allow indexer to index [{}] documents", numDocs);
                indexer.continueIndexing(numDocs);
                logger.info("--> START relocate the shard from {} to {}", nodes[fromNode], nodes[toNode]);
                client().admin().cluster().prepareReroute()
                        .add(new MoveAllocationCommand("test", 0, nodes[fromNode], nodes[toNode]))
                        .get();
                if (rarely()) {
                    logger.debug("--> flushing");
                    client().admin().indices().prepareFlush().get();
                }
                ClusterHealthResponse clusterHealthResponse = client().admin().cluster().prepareHealth().setWaitForEvents(Priority.LANGUID).setWaitForNoRelocatingShards(true).setTimeout(ACCEPTABLE_RELOCATION_TIME).execute().actionGet();
                assertThat(clusterHealthResponse.isTimedOut(), equalTo(false));
                indexer.pauseIndexing();
                logger.info("--> DONE relocate the shard from {} to {}", fromNode, toNode);
            }
            logger.info("--> done relocations");
            logger.info("--> waiting for indexing threads to stop ...");
            indexer.stop();
            logger.info("--> indexing threads stopped");

            logger.info("--> refreshing the index");
            client().admin().indices().prepareRefresh("test").execute().actionGet();
            logger.info("--> searching the index");
            boolean ranOnce = false;
            for (int i = 0; i < 10; i++) {
                    logger.info("--> START search test round {}", i + 1);
                    SearchHits hits = client().prepareSearch("test").setQuery(matchAllQuery()).setSize((int) indexer.totalIndexedDocs()).storedFields().execute().actionGet().getHits();
                    ranOnce = true;
                    if (hits.getTotalHits() != indexer.totalIndexedDocs()) {
                        int[] hitIds = new int[(int) indexer.totalIndexedDocs()];
                        for (int hit = 0; hit < indexer.totalIndexedDocs(); hit++) {
                            hitIds[hit] = hit + 1;
                        }
                        IntHashSet set = IntHashSet.from(hitIds);
                        for (SearchHit hit : hits.getHits()) {
                            int id = Integer.parseInt(hit.getId());
                            if (!set.remove(id)) {
                                logger.error("Extra id [{}]", id);
                            }
                        }
                        set.forEach((IntProcedure) value -> {
                            logger.error("Missing id [{}]", value);
                        });
                    }
                    assertThat(hits.getTotalHits(), equalTo(indexer.totalIndexedDocs()));
                    logger.info("--> DONE search test round {}", i + 1);

            }
            if (!ranOnce) {
                fail();
            }
        }
    }

}