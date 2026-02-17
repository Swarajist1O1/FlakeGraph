class DummyClass_91455 {
@TestLogging("_root:DEBUG,org.elasticsearch.index.shard:TRACE,org.elasticsearch.cluster.service:TRACE,org.elasticsearch.index.seqno:TRACE,org.elasticsearch.indices.recovery:TRACE")
    public void testRecoverWhileUnderLoadAllocateReplicasTest() throws Exception {
        logger.info("--> creating test index ...");
        int numberOfShards = numberOfShards();
        assertAcked(prepareCreate("test", 1, Settings.builder().put(SETTING_NUMBER_OF_SHARDS, numberOfShards).put(SETTING_NUMBER_OF_REPLICAS, 1).put(IndexSettings.INDEX_TRANSLOG_DURABILITY_SETTING.getKey(), Translog.Durability.ASYNC)));

        final int totalNumDocs = scaledRandomIntBetween(200, 10000);
        int waitFor = totalNumDocs / 10;
        int extraDocs = waitFor;
        try (BackgroundIndexer indexer = new BackgroundIndexer("test", "type", client(), extraDocs)) {
            logger.info("--> waiting for {} docs to be indexed ...", waitFor);
            waitForDocs(waitFor, indexer);
            indexer.assertNoFailures();
            logger.info("--> {} docs indexed", waitFor);

            extraDocs = totalNumDocs / 10;
            waitFor += extraDocs;
            indexer.continueIndexing(extraDocs);
            logger.info("--> flushing the index ....");
            // now flush, just to make sure we have some data in the index, not just translog
            client().admin().indices().prepareFlush().execute().actionGet();

            logger.info("--> waiting for {} docs to be indexed ...", waitFor);
            waitForDocs(waitFor, indexer);
            indexer.assertNoFailures();
            logger.info("--> {} docs indexed", waitFor);

            extraDocs = totalNumDocs - waitFor;
            indexer.continueIndexing(extraDocs);

            logger.info("--> allow 2 nodes for index [test] ...");
            // now start another node, while we index
            allowNodes("test", 2);

            logger.info("--> waiting for GREEN health status ...");
            // make sure the cluster state is green, and all has been recovered
            assertNoTimeout(client().admin().cluster().prepareHealth().setWaitForEvents(Priority.LANGUID).setTimeout("5m").setWaitForGreenStatus());

            logger.info("--> waiting for {} docs to be indexed ...", totalNumDocs);
            waitForDocs(totalNumDocs, indexer);
            indexer.assertNoFailures();
            logger.info("--> {} docs indexed", totalNumDocs);

            logger.info("--> marking and waiting for indexing threads to stop ...");
            indexer.stop();
            logger.info("--> indexing threads stopped");

            logger.info("--> refreshing the index");
            refreshAndAssert();
            logger.info("--> verifying indexed content");
            iterateAssertCount(numberOfShards, 10, indexer.getIds());
        }
    }

}