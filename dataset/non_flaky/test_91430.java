class DummyClass_91430 {
@TestLogging("org.elasticsearch.xpack.watcher:DEBUG,org.elasticsearch.xpack.watcher.WatcherIndexingListener:TRACE")
    public void testHttpInput() throws Exception {
        createIndex("index");
        client().prepareIndex("index", "type", "id").setSource("{}", XContentType.JSON).setRefreshPolicy(IMMEDIATE).get();

        InetSocketAddress address = internalCluster().httpAddresses()[0];
        watcherClient().preparePutWatch("_name")
                .setSource(watchBuilder()
                        .trigger(schedule(interval("5s")))
                        .input(httpInput(HttpRequestTemplate.builder(address.getHostString(), address.getPort())
                                .path("/index/_search")
                                .body(Strings.toString(jsonBuilder().startObject().field("size", 1).endObject()))
                                .putHeader("Content-Type", new TextTemplate("application/json"))))
                        .condition(new CompareCondition("ctx.payload.hits.total", CompareCondition.Op.EQ, 1L))
                        .addAction("_id", loggingAction("anything")))
                .get();

        timeWarp().trigger("_name");
        refresh();
        assertWatchWithMinimumPerformedActionsCount("_name", 1, false);
    }

}