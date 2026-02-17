class DummyClass_91471 {
    @TestLogging("org.elasticsearch.cluster.service:WARN") // To ensure that we log cluster state events on WARN level
    public void testLongClusterStateUpdateLogging() throws Exception {
        MockLogAppender mockAppender = new MockLogAppender();
        mockAppender.start();
        mockAppender.addExpectation(
            new MockLogAppender.UnseenEventExpectation(
                "test1 shouldn't see because setting is too low",
                masterService.getClass().getCanonicalName(),
                Level.WARN,
                "*cluster state update task [test1] took [*] above the warn threshold of *"));
        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test2",
                masterService.getClass().getCanonicalName(),
                Level.WARN,
                "*cluster state update task [test2] took [32s] above the warn threshold of *"));
        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test3",
                masterService.getClass().getCanonicalName(),
                Level.WARN,
                "*cluster state update task [test3] took [33s] above the warn threshold of *"));
        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test4",
                masterService.getClass().getCanonicalName(),
                Level.WARN,
                "*cluster state update task [test4] took [34s] above the warn threshold of *"));

        Logger clusterLogger = Loggers.getLogger(masterService.getClass().getPackage().getName());
        Loggers.addAppender(clusterLogger, mockAppender);
        try {
            final CountDownLatch latch = new CountDownLatch(5);
            final CountDownLatch processedFirstTask = new CountDownLatch(1);
            masterService.currentTimeOverride = System.nanoTime();
            masterService.submitStateUpdateTask("test1", new ClusterStateUpdateTask() {
                @Override
                public ClusterState execute(ClusterState currentState) throws Exception {
                    masterService.currentTimeOverride += TimeValue.timeValueSeconds(1).nanos();
                    return currentState;
                }

}