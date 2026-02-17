class DummyClass_91483 {
    @TestLogging("_root:DEBUG,org.elasticsearch.index.shard.IndexShard:TRACE,org.elasticsearch.action.search:TRACE")
    public void testAutoGenerateIdNoDuplicates() throws Exception {
        int numberOfIterations = scaledRandomIntBetween(10, 50);
        for (int i = 0; i < numberOfIterations; i++) {
            Exception firstError = null;
            createIndex("test");
            int numOfDocs = randomIntBetween(10, 100);
            logger.info("indexing [{}] docs", numOfDocs);
            List<IndexRequestBuilder> builders = new ArrayList<>(numOfDocs);
            for (int j = 0; j < numOfDocs; j++) {
                builders.add(client().prepareIndex("test", "type").setSource("field", "value_" + j));
            }
            indexRandom(true, builders);
            logger.info("verifying indexed content");
            int numOfChecks = randomIntBetween(8, 12);
            for (int j = 0; j < numOfChecks; j++) {
                try {
                    logger.debug("running search with all types");
                    SearchResponse response = client().prepareSearch("test").get();
                    if (response.getHits().getTotalHits() != numOfDocs) {
                        final String message = "Count is " + response.getHits().getTotalHits() + " but " + numOfDocs + " was expected. "
                            + ElasticsearchAssertions.formatShardStatus(response);
                        logger.error("{}. search response: \n{}", message, response);
                        fail(message);
                    }
                } catch (Exception e) {
                    logger.error("search for all docs types failed", e);
                    if (firstError == null) {
                        firstError = e;
                    }
                }
                try {
                    logger.debug("running search with a specific type");
                    SearchResponse response = client().prepareSearch("test").setTypes("type").get();
                    if (response.getHits().getTotalHits() != numOfDocs) {
                        final String message = "Count is " + response.getHits().getTotalHits() + " but " + numOfDocs + " was expected. "
                            + ElasticsearchAssertions.formatShardStatus(response);
                        logger.error("{}. search response: \n{}", message, response);
                        fail(message);
                    }
                } catch (Exception e) {
                    logger.error("search for all docs of a specific type failed", e);
                    if (firstError == null) {
                        firstError = e;
                    }
                }
            }
            if (firstError != null) {
                fail(firstError.getMessage());
            }
            internalCluster().wipeIndices("test");
        }
    }

}