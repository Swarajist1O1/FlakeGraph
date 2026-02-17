class DummyClass_77473 {
    @TestLogging(value = "org.opensearch.cluster.service:WARN", reason = "to ensure that we log cluster state events on WARN level")
    public void testLongClusterStateUpdateLogging() throws Exception {
        MockLogAppender mockAppender = new MockLogAppender();
        mockAppender.start();
        mockAppender.addExpectation(
                new MockLogAppender.UnseenEventExpectation(
                        "test1 shouldn't see because setting is too low",
                        ClusterApplierService.class.getCanonicalName(),
                        Level.WARN,
                        "*cluster state applier task [test1] took [*] which is above the warn threshold of *"));
        mockAppender.addExpectation(
                new MockLogAppender.SeenEventExpectation(
                        "test2",
                        ClusterApplierService.class.getCanonicalName(),
                        Level.WARN,
                        "*cluster state applier task [test2] took [32s] which is above the warn threshold of [*]: " +
                            "[running task [test2]] took [*"));
        mockAppender.addExpectation(
                new MockLogAppender.SeenEventExpectation(
                        "test4",
                        ClusterApplierService.class.getCanonicalName(),
                        Level.WARN,
                        "*cluster state applier task [test3] took [34s] which is above the warn threshold of [*]: " +
                            "[running task [test3]] took [*"));

        Logger clusterLogger = LogManager.getLogger(ClusterApplierService.class);
        Loggers.addAppender(clusterLogger, mockAppender);
        try {
            final CountDownLatch latch = new CountDownLatch(4);
            final CountDownLatch processedFirstTask = new CountDownLatch(1);
            clusterApplierService.currentTimeOverride = threadPool.relativeTimeInMillis();
            clusterApplierService.runOnApplierThread("test1",
                currentState -> clusterApplierService.currentTimeOverride += TimeValue.timeValueSeconds(1).millis(),
                new ClusterApplyListener() {
                    @Override
                    public void onSuccess(String source) {
                        latch.countDown();
                        processedFirstTask.countDown();
                    }

}