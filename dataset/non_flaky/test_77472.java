class DummyClass_77472 {
    @TestLogging(value = "org.opensearch.cluster.service:TRACE", reason = "to ensure that we log cluster state events on TRACE level")
    public void testClusterStateUpdateLogging() throws Exception {
        MockLogAppender mockAppender = new MockLogAppender();
        mockAppender.start();
        mockAppender.addExpectation(
                new MockLogAppender.SeenEventExpectation(
                        "test1",
                        ClusterApplierService.class.getCanonicalName(),
                        Level.DEBUG,
                        "*processing [test1]: took [1s] no change in cluster state"));
        mockAppender.addExpectation(
                new MockLogAppender.SeenEventExpectation(
                        "test2",
                        ClusterApplierService.class.getCanonicalName(),
                        Level.TRACE,
                        "*failed to execute cluster state applier in [2s]*"));
        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test3",
                ClusterApplierService.class.getCanonicalName(),
                Level.DEBUG,
                "*processing [test3]: took [0s] no change in cluster state*"));

        Logger clusterLogger = LogManager.getLogger(ClusterApplierService.class);
        Loggers.addAppender(clusterLogger, mockAppender);
        try {
            clusterApplierService.currentTimeOverride = threadPool.relativeTimeInMillis();
            clusterApplierService.runOnApplierThread("test1",
                currentState -> clusterApplierService.currentTimeOverride += TimeValue.timeValueSeconds(1).millis(),
                new ClusterApplyListener() {
                    @Override
                    public void onSuccess(String source) { }


}