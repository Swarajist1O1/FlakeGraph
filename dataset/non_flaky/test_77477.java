class DummyClass_77477 {
    @TestLogging(value = "org.opensearch.gateway:WARN", reason = "to ensure that we log gateway events on WARN level")
    public void testSlowLogging() throws IOException, IllegalAccessException {
        final long slowWriteLoggingThresholdMillis;
        final Settings settings;
        if (randomBoolean()) {
            slowWriteLoggingThresholdMillis = PersistedClusterStateService.SLOW_WRITE_LOGGING_THRESHOLD.get(Settings.EMPTY).millis();
            settings = Settings.EMPTY;
        } else {
            slowWriteLoggingThresholdMillis = randomLongBetween(2, 100000);
            settings = Settings.builder()
                .put(PersistedClusterStateService.SLOW_WRITE_LOGGING_THRESHOLD.getKey(), slowWriteLoggingThresholdMillis + "ms")
                .build();
        }

        final DiscoveryNode localNode = new DiscoveryNode("node", buildNewFakeTransportAddress(), Version.CURRENT);
        final ClusterState clusterState = ClusterState.builder(ClusterName.DEFAULT)
            .nodes(DiscoveryNodes.builder().add(localNode).localNodeId(localNode.getId())).build();

        final long startTimeMillis = randomLongBetween(0L, Long.MAX_VALUE - slowWriteLoggingThresholdMillis * 10);
        final AtomicLong currentTime = new AtomicLong(startTimeMillis);
        final AtomicLong writeDurationMillis = new AtomicLong(slowWriteLoggingThresholdMillis);

        final ClusterSettings clusterSettings = new ClusterSettings(settings, ClusterSettings.BUILT_IN_CLUSTER_SETTINGS);
        try (NodeEnvironment nodeEnvironment = newNodeEnvironment(createDataPaths())) {
            PersistedClusterStateService persistedClusterStateService = new PersistedClusterStateService(nodeEnvironment,
                    xContentRegistry(), getBigArrays(), clusterSettings, () -> currentTime.getAndAdd(writeDurationMillis.get()));

            try (Writer writer = persistedClusterStateService.createWriter()) {
                assertExpectedLogs(1L, null, clusterState, writer, new MockLogAppender.SeenEventExpectation(
                    "should see warning at threshold",
                    PersistedClusterStateService.class.getCanonicalName(),
                    Level.WARN,
                    "writing cluster state took [*] which is above the warn threshold of [*]; " +
                        "wrote full state with [0] indices"));

                writeDurationMillis.set(randomLongBetween(slowWriteLoggingThresholdMillis, slowWriteLoggingThresholdMillis * 2));
                assertExpectedLogs(1L, null, clusterState, writer, new MockLogAppender.SeenEventExpectation(
                    "should see warning above threshold",
                    PersistedClusterStateService.class.getCanonicalName(),
                    Level.WARN,
                    "writing cluster state took [*] which is above the warn threshold of [*]; " +
                        "wrote full state with [0] indices"));

                writeDurationMillis.set(randomLongBetween(1, slowWriteLoggingThresholdMillis - 1));
                assertExpectedLogs(1L, null, clusterState, writer, new MockLogAppender.UnseenEventExpectation(
                    "should not see warning below threshold",
                    PersistedClusterStateService.class.getCanonicalName(),
                    Level.WARN,
                    "*"));

                clusterSettings.applySettings(Settings.builder()
                    .put(PersistedClusterStateService.SLOW_WRITE_LOGGING_THRESHOLD.getKey(), writeDurationMillis.get() + "ms")
                    .build());
                assertExpectedLogs(1L, null, clusterState, writer, new MockLogAppender.SeenEventExpectation(
                    "should see warning at reduced threshold",
                    PersistedClusterStateService.class.getCanonicalName(),
                    Level.WARN,
                    "writing cluster state took [*] which is above the warn threshold of [*]; " +
                        "wrote full state with [0] indices"));

                final ClusterState newClusterState = ClusterState.builder(clusterState)
                    .metadata(Metadata.builder(clusterState.metadata())
                        .version(clusterState.version())
                        .put(IndexMetadata.builder("test")
                            .settings(Settings.builder()
                                .put(IndexMetadata.INDEX_NUMBER_OF_SHARDS_SETTING.getKey(), 1)
                                .put(IndexMetadata.INDEX_NUMBER_OF_REPLICAS_SETTING.getKey(), 0)
                                .put(IndexMetadata.SETTING_INDEX_VERSION_CREATED.getKey(), Version.CURRENT)
                                .put(IndexMetadata.SETTING_INDEX_UUID, "test-uuid"))))
                    .incrementVersion().build();

                assertExpectedLogs(1L, clusterState, newClusterState, writer, new MockLogAppender.SeenEventExpectation(
                    "should see warning at threshold",
                    PersistedClusterStateService.class.getCanonicalName(),
                    Level.WARN,
                    "writing cluster state took [*] which is above the warn threshold of [*]; " +
                        "wrote global metadata [false] and metadata for [1] indices and skipped [0] unchanged indices"));

                writeDurationMillis.set(randomLongBetween(0, writeDurationMillis.get() - 1));
                assertExpectedLogs(1L, clusterState, newClusterState, writer, new MockLogAppender.UnseenEventExpectation(
                    "should not see warning below threshold",
                    PersistedClusterStateService.class.getCanonicalName(),
                    Level.WARN,
                    "*"));

                assertThat(currentTime.get(), lessThan(startTimeMillis + 14 * slowWriteLoggingThresholdMillis)); // ensure no overflow
            }
        }
    }

}