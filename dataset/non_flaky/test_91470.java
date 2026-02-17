class DummyClass_91470 {
    @TestLogging("org.elasticsearch.cluster.service:TRACE") // To ensure that we log cluster state events on TRACE level
    public void testClusterStateUpdateLogging() throws Exception {
        MockLogAppender mockAppender = new MockLogAppender();
        mockAppender.start();
        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test1",
                masterService.getClass().getCanonicalName(),
                Level.DEBUG,
                "*processing [test1]: took [1s] no change in cluster state"));
        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test2",
                masterService.getClass().getCanonicalName(),
                Level.TRACE,
                "*failed to execute cluster state update in [2s]*"));
        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test3",
                masterService.getClass().getCanonicalName(),
                Level.DEBUG,
                "*processing [test3]: took [3s] done publishing updated cluster state (version: *, uuid: *)"));

        Logger clusterLogger = Loggers.getLogger(masterService.getClass().getPackage().getName());
        Loggers.addAppender(clusterLogger, mockAppender);
        try {
            final CountDownLatch latch = new CountDownLatch(4);
            masterService.currentTimeOverride = System.nanoTime();
            masterService.submitStateUpdateTask("test1", new ClusterStateUpdateTask() {
                @Override
                public ClusterState execute(ClusterState currentState) throws Exception {
                    masterService.currentTimeOverride += TimeValue.timeValueSeconds(1).nanos();
                    return currentState;
                }

}