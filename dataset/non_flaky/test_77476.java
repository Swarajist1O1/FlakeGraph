class DummyClass_77476 {
    @TestLogging(value = "org.opensearch.gateway:WARN", reason = "to ensure that we log gateway events on WARN level")
    public void testSlowLogging() throws WriteStateException, IllegalAccessException {
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

        final DiscoveryNode localNode = newNode("node");
        final ClusterState clusterState = ClusterState.builder(ClusterName.DEFAULT)
            .nodes(DiscoveryNodes.builder().add(localNode).localNodeId(localNode.getId())).build();

        final long startTimeMillis = randomLongBetween(0L, Long.MAX_VALUE - slowWriteLoggingThresholdMillis * 10);
        final AtomicLong currentTime = new AtomicLong(startTimeMillis);
        final AtomicLong writeDurationMillis = new AtomicLong(slowWriteLoggingThresholdMillis);

        final ClusterSettings clusterSettings = new ClusterSettings(settings, ClusterSettings.BUILT_IN_CLUSTER_SETTINGS);
        final IncrementalClusterStateWriter incrementalClusterStateWriter
            = new IncrementalClusterStateWriter(settings, clusterSettings, mock(MetaStateService.class),
            new Manifest(randomNonNegativeLong(), randomNonNegativeLong(), randomNonNegativeLong(), Collections.emptyMap()),
            clusterState, () -> currentTime.getAndAdd(writeDurationMillis.get()));

        assertExpectedLogs(clusterState, incrementalClusterStateWriter, new MockLogAppender.SeenEventExpectation(
            "should see warning at threshold",
            IncrementalClusterStateWriter.class.getCanonicalName(),
            Level.WARN,
            "writing cluster state took [*] which is above the warn threshold of [*]; " +
                "wrote metadata for [0] indices and skipped [0] unchanged indices"));

        writeDurationMillis.set(randomLongBetween(slowWriteLoggingThresholdMillis, slowWriteLoggingThresholdMillis * 2));
        assertExpectedLogs(clusterState, incrementalClusterStateWriter, new MockLogAppender.SeenEventExpectation(
            "should see warning above threshold",
            IncrementalClusterStateWriter.class.getCanonicalName(),
            Level.WARN,
            "writing cluster state took [*] which is above the warn threshold of [*]; " +
                "wrote metadata for [0] indices and skipped [0] unchanged indices"));

        writeDurationMillis.set(randomLongBetween(1, slowWriteLoggingThresholdMillis - 1));
        assertExpectedLogs(clusterState, incrementalClusterStateWriter, new MockLogAppender.UnseenEventExpectation(
            "should not see warning below threshold",
            IncrementalClusterStateWriter.class.getCanonicalName(),
            Level.WARN,
            "*"));

        clusterSettings.applySettings(Settings.builder()
            .put(PersistedClusterStateService.SLOW_WRITE_LOGGING_THRESHOLD.getKey(), writeDurationMillis.get() + "ms")
            .build());
        assertExpectedLogs(clusterState, incrementalClusterStateWriter, new MockLogAppender.SeenEventExpectation(
            "should see warning at reduced threshold",
            IncrementalClusterStateWriter.class.getCanonicalName(),
            Level.WARN,
            "writing cluster state took [*] which is above the warn threshold of [*]; " +
                "wrote metadata for [0] indices and skipped [0] unchanged indices"));

        assertThat(currentTime.get(), lessThan(startTimeMillis + 10 * slowWriteLoggingThresholdMillis)); // ensure no overflow
    }

}