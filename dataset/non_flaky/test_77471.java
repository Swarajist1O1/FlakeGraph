class DummyClass_77471 {
    @TestLogging(value = "org.opensearch.cluster.service:WARN", reason = "to ensure that we log cluster state events on WARN level")
    public void testLongClusterStateUpdateLogging() throws Exception {
        MockLogAppender mockAppender = new MockLogAppender();
        mockAppender.start();
        mockAppender.addExpectation(
            new MockLogAppender.UnseenEventExpectation(
                "test1 shouldn't log because it was fast enough",
                MasterService.class.getCanonicalName(),
                Level.WARN,
                "*took*test1*"));
        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test2",
                MasterService.class.getCanonicalName(),
                Level.WARN,
                "*took [*], which is over [10s], to compute cluster state update for [test2]"));
        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test3",
                MasterService.class.getCanonicalName(),
                Level.WARN,
                "*took [*], which is over [10s], to compute cluster state update for [test3]"));
        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test4",
                MasterService.class.getCanonicalName(),
                Level.WARN,
                "*took [*], which is over [10s], to compute cluster state update for [test4]"));
        mockAppender.addExpectation(
            new MockLogAppender.UnseenEventExpectation(
                "test5 should not log despite publishing slowly",
                MasterService.class.getCanonicalName(),
                Level.WARN,
                "*took*test5*"));
        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "test6 should log due to slow and failing publication",
                MasterService.class.getCanonicalName(),
                Level.WARN,
                "took [*] and then failed to publish updated cluster state (version: *, uuid: *) for [test6]:*"));

        Logger clusterLogger = LogManager.getLogger(MasterService.class);
        Loggers.addAppender(clusterLogger, mockAppender);
        try (MasterService masterService = new MasterService(Settings.builder()
            .put(ClusterName.CLUSTER_NAME_SETTING.getKey(), MasterServiceTests.class.getSimpleName())
            .put(Node.NODE_NAME_SETTING.getKey(), "test_node")
            .build(), new ClusterSettings(Settings.EMPTY, ClusterSettings.BUILT_IN_CLUSTER_SETTINGS), threadPool)) {

            final DiscoveryNode localNode = new DiscoveryNode("node1", buildNewFakeTransportAddress(), emptyMap(),
                emptySet(), Version.CURRENT);
            final ClusterState initialClusterState = ClusterState.builder(new ClusterName(MasterServiceTests.class.getSimpleName()))
                .nodes(DiscoveryNodes.builder().add(localNode).localNodeId(localNode.getId()).masterNodeId(localNode.getId()))
                .blocks(ClusterBlocks.EMPTY_CLUSTER_BLOCK).build();
            final AtomicReference<ClusterState> clusterStateRef = new AtomicReference<>(initialClusterState);
            masterService.setClusterStatePublisher((event, publishListener, ackListener) -> {
                if (event.source().contains("test5")) {
                    relativeTimeInMillis += MasterService.MASTER_SERVICE_SLOW_TASK_LOGGING_THRESHOLD_SETTING.get(Settings.EMPTY).millis()
                        + randomLongBetween(1, 1000000);
                }
                if (event.source().contains("test6")) {
                    relativeTimeInMillis += MasterService.MASTER_SERVICE_SLOW_TASK_LOGGING_THRESHOLD_SETTING.get(Settings.EMPTY).millis()
                        + randomLongBetween(1, 1000000);
                    throw new OpenSearchException("simulated error during slow publication which should trigger logging");
                }
                clusterStateRef.set(event.state());
                publishListener.onResponse(null);
            });
            masterService.setClusterStateSupplier(clusterStateRef::get);
            masterService.start();

            final CountDownLatch latch = new CountDownLatch(6);
            final CountDownLatch processedFirstTask = new CountDownLatch(1);
            masterService.submitStateUpdateTask("test1", new ClusterStateUpdateTask() {
                @Override
                public ClusterState execute(ClusterState currentState) {
                    relativeTimeInMillis += randomLongBetween(0L,
                        MasterService.MASTER_SERVICE_SLOW_TASK_LOGGING_THRESHOLD_SETTING.get(Settings.EMPTY).millis());
                    return currentState;
                }

}