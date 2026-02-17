class DummyClass_91473 {
    @TestLogging("org.elasticsearch.cluster.service:WARN") // To ensure that we log cluster state events on WARN level
    public void testLongClusterStateUpdateLogging() throws Exception {
        MockLogAppender mockAppender = new MockLogAppender();
        mockAppender.start();
        mockAppender.addExpectation(
                new MockLogAppender.UnseenEventExpectation(
                        "test1 shouldn't see because setting is too low",
                        clusterApplierService.getClass().getCanonicalName(),
                        Level.WARN,
                        "*cluster state applier task [test1] took [*] above the warn threshold of *"));
        mockAppender.addExpectation(
                new MockLogAppender.SeenEventExpectation(
                        "test2",
                        clusterApplierService.getClass().getCanonicalName(),
                        Level.WARN,
                        "*cluster state applier task [test2] took [32s] above the warn threshold of *"));
        mockAppender.addExpectation(
                new MockLogAppender.SeenEventExpectation(
                        "test4",
                        clusterApplierService.getClass().getCanonicalName(),
                        Level.WARN,
                        "*cluster state applier task [test3] took [34s] above the warn threshold of *"));

        Logger clusterLogger = Loggers.getLogger("org.elasticsearch.cluster.service");
        Loggers.addAppender(clusterLogger, mockAppender);
        try {
            final CountDownLatch latch = new CountDownLatch(4);
            final CountDownLatch processedFirstTask = new CountDownLatch(1);
            clusterApplierService.currentTimeOverride = System.nanoTime();
            clusterApplierService.runOnApplierThread("test1",
                currentState -> clusterApplierService.currentTimeOverride += TimeValue.timeValueSeconds(1).nanos(),
                new ClusterApplyListener() {
                    @Override
                    public void onSuccess(String source) {
                        latch.countDown();
                        processedFirstTask.countDown();
                    }

}