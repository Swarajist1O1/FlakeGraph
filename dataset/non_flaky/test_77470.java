class DummyClass_77470 {
    @TestLogging(value = "org.opensearch.cluster.service:TRACE", reason = "to ensure that we log cluster state events on TRACE level")
    public void testClusterStateUpdateLogging() throws Exception {
        MockLogAppender mockAppender = new MockLogAppender();
        mockAppender.start();
        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test1 start",
                MasterService.class.getCanonicalName(),
                Level.DEBUG,
                "executing cluster state update for [test1]"));
        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test1 computation",
                MasterService.class.getCanonicalName(),
                Level.DEBUG,
                "took [1s] to compute cluster state update for [test1]"));
        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test1 notification",
                MasterService.class.getCanonicalName(),
                Level.DEBUG,
                "took [0s] to notify listeners on unchanged cluster state for [test1]"));

        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test2 start",
                MasterService.class.getCanonicalName(),
                Level.DEBUG,
                "executing cluster state update for [test2]"));
        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test2 failure",
                MasterService.class.getCanonicalName(),
                Level.TRACE,
                "failed to execute cluster state update (on version: [*], uuid: [*]) for [test2]*"));
        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test2 computation",
                MasterService.class.getCanonicalName(),
                Level.DEBUG,
                "took [2s] to compute cluster state update for [test2]"));
        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test2 notification",
                MasterService.class.getCanonicalName(),
                Level.DEBUG,
                "took [0s] to notify listeners on unchanged cluster state for [test2]"));

        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test3 start",
                MasterService.class.getCanonicalName(),
                Level.DEBUG,
                "executing cluster state update for [test3]"));
        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test3 computation",
                MasterService.class.getCanonicalName(),
                Level.DEBUG,
                "took [3s] to compute cluster state update for [test3]"));
        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test3 notification",
                MasterService.class.getCanonicalName(),
                Level.DEBUG,
                "took [4s] to notify listeners on successful publication of cluster state (version: *, uuid: *) for [test3]"));

        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test4",
                MasterService.class.getCanonicalName(),
                Level.DEBUG,
                "executing cluster state update for [test4]"));

        Logger clusterLogger = LogManager.getLogger(MasterService.class);
        Loggers.addAppender(clusterLogger, mockAppender);
        try (MasterService masterService = createMasterService(true)) {
            masterService.submitStateUpdateTask("test1", new ClusterStateUpdateTask() {
                @Override
                public ClusterState execute(ClusterState currentState) {
                    relativeTimeInMillis += TimeValue.timeValueSeconds(1).millis();
                    return currentState;
                }

}