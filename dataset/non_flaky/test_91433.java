class DummyClass_91433 {
@TestLogging("org.elasticsearch.xpack.watcher:DEBUG")
    public void indexTestDocument() {
        IndexResponse eventIndexResponse = client().prepareIndex("events", "event", id)
                .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE)
                .setSource("level", "error")
                .get();
        assertEquals(DocWriteResponse.Result.CREATED, eventIndexResponse.getResult());
    }

}