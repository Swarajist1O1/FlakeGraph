class DummyClass_91459 {
    @TestLogging(
    public void testIndexAndRelocateConcurrently() throws ExecutionException, InterruptedException {
        int halfNodes = randomIntBetween(1, 3);
        Settings[] nodeSettings = Stream.concat(
            Stream.generate(() -> Settings.builder().put("node.attr.color", "blue").build()).limit(halfNodes),
            Stream.generate(() -> Settings.builder().put("node.attr.color", "red").build()).limit(halfNodes)
            ).toArray(Settings[]::new);
        List<String> nodes = internalCluster().startNodes(nodeSettings);
        String[] blueNodes = nodes.subList(0, halfNodes).stream().toArray(String[]::new);
        String[] redNodes = nodes.subList(halfNodes, nodes.size()).stream().toArray(String[]::new);
        logger.info("blue nodes: {}", (Object)blueNodes);
        logger.info("red nodes: {}", (Object)redNodes);
        ensureStableCluster(halfNodes * 2);

        final Settings.Builder settings = Settings.builder()
                .put("index.routing.allocation.exclude.color", "blue")
                .put(indexSettings())
                .put(IndexMetaData.SETTING_NUMBER_OF_REPLICAS, randomInt(halfNodes - 1))
                .put(IndexService.GLOBAL_CHECKPOINT_SYNC_INTERVAL_SETTING.getKey(), "100ms");
        assertAcked(prepareCreate("test", settings));
        assertAllShardsOnNodes("test", redNodes);
        int numDocs = randomIntBetween(100, 150);
        ArrayList<String> ids = new ArrayList<>();
        logger.info(" --> indexing [{}] docs", numDocs);
        IndexRequestBuilder[] docs = new IndexRequestBuilder[numDocs];
        for (int i = 0; i < numDocs; i++) {
            String id = randomRealisticUnicodeOfLength(10) + String.valueOf(i);
            ids.add(id);
            docs[i] = client().prepareIndex("test", "type1", id).setSource("field1", English.intToEnglish(i));
        }
        indexRandom(true, docs);
        SearchResponse countResponse = client().prepareSearch("test").get();
        assertHitCount(countResponse, numDocs);

        logger.info(" --> moving index to new nodes");
        Settings build = Settings.builder().put("index.routing.allocation.exclude.color", "red")
            .put("index.routing.allocation.include.color", "blue").build();
        client().admin().indices().prepareUpdateSettings("test").setSettings(build).execute().actionGet();

        // index while relocating
        logger.info(" --> indexing [{}] more docs", numDocs);
        for (int i = 0; i < numDocs; i++) {
            String id = randomRealisticUnicodeOfLength(10) + String.valueOf(numDocs + i);
            ids.add(id);
            docs[i] = client().prepareIndex("test", "type1", id).setSource("field1", English.intToEnglish(numDocs + i));
        }
        indexRandom(true, docs);
        numDocs *= 2;

        logger.info(" --> waiting for relocation to complete");
        ensureGreen("test"); // move all shards to the new nodes (it waits on relocation)

        final int numIters = randomIntBetween(10, 20);
        for (int i = 0; i < numIters; i++) {
            logger.info(" --> checking iteration {}", i);
            SearchResponse afterRelocation = client().prepareSearch().setSize(ids.size()).get();
            assertNoFailures(afterRelocation);
            assertSearchHits(afterRelocation, ids.toArray(new String[ids.size()]));
        }

    }

}