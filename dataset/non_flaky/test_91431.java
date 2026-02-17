class DummyClass_91431 {
@TestLogging("org.elasticsearch.xpack.watcher:DEBUG," +
    public void testIndexWatch() throws Exception {
        WatcherClient watcherClient = watcherClient();
        createIndex("idx");
        // Have a sample document in the index, the watch is going to evaluate
        client().prepareIndex("idx", "type").setSource("field", "foo").get();
        refresh();
        WatcherSearchTemplateRequest request = templateRequest(searchSource().query(termQuery("field", "foo")), "idx");
        watcherClient.preparePutWatch("_name")
                .setSource(watchBuilder()
                        .trigger(schedule(interval(5, IntervalSchedule.Interval.Unit.SECONDS)))
                        .input(searchInput(request))
                        .condition(new CompareCondition("ctx.payload.hits.total", CompareCondition.Op.EQ, 1L))
                        .addAction("_logger", loggingAction("_logging")
                                .setCategory("_category")))
                .get();

        timeWarp().trigger("_name");
        assertWatchWithMinimumPerformedActionsCount("_name", 1);

        GetWatchResponse getWatchResponse = watcherClient().prepareGetWatch().setId("_name").get();
        assertThat(getWatchResponse.isFound(), is(true));
        assertThat(getWatchResponse.getSource(), notNullValue());
    }

}