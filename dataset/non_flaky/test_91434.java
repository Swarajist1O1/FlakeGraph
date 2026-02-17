class DummyClass_91434 {
@TestLogging("org.elasticsearch.xpack.watcher:DEBUG," +
    public void testTimeThrottle(){
        String id = randomAlphaOfLength(20);
        PutWatchResponse putWatchResponse = watcherClient().preparePutWatch()
                .setId(id)
                .setSource(watchBuilder()
                        .trigger(schedule(interval("5s")))
                        .input(simpleInput())
                        .addAction("my-logging-action", loggingAction("foo"))
                        .defaultThrottlePeriod(TimeValue.timeValueSeconds(30)))
                .get();
        assertThat(putWatchResponse.isCreated(), is(true));

        timeWarp().trigger(id);
        assertHistoryEntryExecuted(id);

        timeWarp().clock().fastForward(TimeValue.timeValueMillis(4000));
        timeWarp().trigger(id);
        assertHistoryEntryThrottled(id);

        timeWarp().clock().fastForwardSeconds(30);
        timeWarp().trigger(id);
        assertHistoryEntryExecuted(id);

        assertTotalHistoryEntries(id, 3);
    }

}