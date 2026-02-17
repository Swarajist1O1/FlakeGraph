class DummyClass_77475 {
    @TestLogging(value="org.opensearch.cluster.routing.allocation.DiskThresholdMonitor:INFO", reason="testing INFO/WARN logging")
    public void testDiskMonitorLogging() throws IllegalAccessException {
        final ClusterState clusterState = ClusterState.builder(ClusterName.CLUSTER_NAME_SETTING.getDefault(Settings.EMPTY))
            .nodes(DiscoveryNodes.builder().add(newNode("node1"))).build();
        final AtomicReference<ClusterState> clusterStateRef = new AtomicReference<>(clusterState);
        final AtomicBoolean advanceTime = new AtomicBoolean(randomBoolean());

        final LongSupplier timeSupplier = new LongSupplier() {
            long time;

            @Override
            public long getAsLong() {
                if (advanceTime.get()) {
                    time += DiskThresholdSettings.CLUSTER_ROUTING_ALLOCATION_REROUTE_INTERVAL_SETTING.get(Settings.EMPTY).getMillis() + 1;
                }
                logger.info("time: [{}]", time);
                return time;
            }

}