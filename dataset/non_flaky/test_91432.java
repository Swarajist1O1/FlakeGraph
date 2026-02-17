class DummyClass_91432 {
    @TestLogging("org.elasticsearch.xpack.watcher:DEBUG")
    public void testModifyWatches() throws Exception {
        createIndex("idx");
        WatcherSearchTemplateRequest searchRequest = templateRequest(searchSource().query(matchAllQuery()), "idx");

        WatchSourceBuilder source = watchBuilder()
                .trigger(schedule(interval("5s")))
                .input(searchInput(searchRequest))
                .addAction("_id", indexAction("idx", "action"));

        watcherClient().preparePutWatch("_name")
                .setSource(source.condition(new CompareCondition("ctx.payload.hits.total", CompareCondition.Op.EQ, 1L)))
                .get();

        timeWarp().clock().fastForwardSeconds(5);
        timeWarp().trigger("_name");
        assertWatchWithMinimumPerformedActionsCount("_name", 0, false);

        watcherClient().preparePutWatch("_name")
                .setSource(source.condition(new CompareCondition("ctx.payload.hits.total", CompareCondition.Op.EQ, 0L)))
                .get();

        timeWarp().clock().fastForwardSeconds(5);
        timeWarp().trigger("_name");
        refresh();
        assertWatchWithMinimumPerformedActionsCount("_name", 1, false);

        watcherClient().preparePutWatch("_name")
                .setSource(source
                        .trigger(schedule(Schedules.cron("0/1 * * * * ? 2020")))
                        .condition(new CompareCondition("ctx.payload.hits.total", CompareCondition.Op.EQ, 0L)))
                .get();

        timeWarp().clock().fastForwardSeconds(5);
        timeWarp().trigger("_name");
        long count = findNumberOfPerformedActions("_name");

        timeWarp().clock().fastForwardSeconds(5);
        timeWarp().trigger("_name");
        assertThat(count, equalTo(findNumberOfPerformedActions("_name")));
    }

}