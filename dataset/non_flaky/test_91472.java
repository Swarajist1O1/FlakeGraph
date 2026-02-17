class DummyClass_91472 {
    @TestLogging("org.elasticsearch.cluster.service:TRACE") // To ensure that we log cluster state events on TRACE level
    public void testClusterStateUpdateLogging() throws Exception {
        MockLogAppender mockAppender = new MockLogAppender();
        mockAppender.start();
        mockAppender.addExpectation(
                new MockLogAppender.SeenEventExpectation(
                        "test1",
                        clusterApplierService.getClass().getCanonicalName(),
                        Level.DEBUG,
                        "*processing [test1]: took [1s] no change in cluster state"));
        mockAppender.addExpectation(
                new MockLogAppender.SeenEventExpectation(
                        "test2",
                        clusterApplierService.getClass().getCanonicalName(),
                        Level.TRACE,
                        "*failed to execute cluster state applier in [2s]*"));

        Logger clusterLogger = Loggers.getLogger("org.elasticsearch.cluster.service");
        Loggers.addAppender(clusterLogger, mockAppender);
        try {
            final CountDownLatch latch = new CountDownLatch(3);
            clusterApplierService.currentTimeOverride = System.nanoTime();
            clusterApplierService.runOnApplierThread("test1",
                currentState -> clusterApplierService.currentTimeOverride += TimeValue.timeValueSeconds(1).nanos(),
                new ClusterApplyListener() {
                    @Override
                    public void onSuccess(String source) {
                        latch.countDown();
                    }

}