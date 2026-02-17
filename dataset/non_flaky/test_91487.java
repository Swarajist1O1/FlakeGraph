class DummyClass_91487 {
    @TestLogging("_root:DEBUG,org.elasticsearch.action.bulk:TRACE,org.elasticsearch.index.shard:TRACE,org.elasticsearch.cluster.service:TRACE")
    public void testPrimaryRelocationWhileIndexing() throws Exception {
        internalCluster().ensureAtLeastNumDataNodes(randomIntBetween(2, 3));
        client().admin().indices().prepareCreate("test")
            .setSettings(Settings.builder().put("index.number_of_shards", 1).put("index.number_of_replicas", 0))
            .addMapping("type", "field", "type=text")
            .get();
        ensureGreen("test");
        AtomicInteger numAutoGenDocs = new AtomicInteger();
        final AtomicBoolean finished = new AtomicBoolean(false);
        Thread indexingThread = new Thread() {
            @Override
            public void run() {
                while (finished.get() == false) {
                    IndexResponse indexResponse = client().prepareIndex("test", "type", "id").setSource("field", "value").get();
                    assertEquals(DocWriteResponse.Result.CREATED, indexResponse.getResult());
                    DeleteResponse deleteResponse = client().prepareDelete("test", "type", "id").get();
                    assertEquals(DocWriteResponse.Result.DELETED, deleteResponse.getResult());
                    client().prepareIndex("test", "type").setSource("auto", true).get();
                    numAutoGenDocs.incrementAndGet();
                }
            }

}