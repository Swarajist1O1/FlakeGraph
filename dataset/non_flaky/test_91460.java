class DummyClass_91460 {
@TestLogging("org.elasticsearch.snapshot:TRACE")
    public void testDisruptionOnSnapshotInitialization() throws Exception {
        final Settings settings = Settings.builder()
            .put(DEFAULT_SETTINGS)
            .put(DiscoverySettings.COMMIT_TIMEOUT_SETTING.getKey(), "30s") // wait till cluster state is committed
            .build();
        final String idxName = "test";
        configureCluster(settings, 4, null, 2);
        final List<String> allMasterEligibleNodes = internalCluster().startMasterOnlyNodes(3);
        final String dataNode = internalCluster().startDataOnlyNode();
        ensureStableCluster(4);

        createRandomIndex(idxName);

        logger.info("-->  creating repository");
        assertAcked(client().admin().cluster().preparePutRepository("test-repo")
            .setType("fs").setSettings(Settings.builder()
                .put("location", randomRepoPath())
                .put("compress", randomBoolean())
                .put("chunk_size", randomIntBetween(100, 1000), ByteSizeUnit.BYTES)));

        // Writing incompatible snapshot can cause this test to fail due to a race condition in repo initialization
        // by the current master and the former master. It is not causing any issues in real life scenario, but
        // might make this test to fail. We are going to complete initialization of the snapshot to prevent this failures.
        logger.info("-->  initializing the repository");
        assertEquals(SnapshotState.SUCCESS, client().admin().cluster().prepareCreateSnapshot("test-repo", "test-snap-1")
            .setWaitForCompletion(true).setIncludeGlobalState(true).setIndices().get().getSnapshotInfo().state());

        final String masterNode1 = internalCluster().getMasterName();
        Set<String> otherNodes = new HashSet<>();
        otherNodes.addAll(allMasterEligibleNodes);
        otherNodes.remove(masterNode1);
        otherNodes.add(dataNode);

        NetworkDisruption networkDisruption =
            new NetworkDisruption(new NetworkDisruption.TwoPartitions(Collections.singleton(masterNode1), otherNodes),
                new NetworkDisruption.NetworkUnresponsive());
        internalCluster().setDisruptionScheme(networkDisruption);

        ClusterService clusterService = internalCluster().clusterService(masterNode1);
        CountDownLatch disruptionStarted = new CountDownLatch(1);
        clusterService.addListener(new ClusterStateListener() {
            @Override
            public void clusterChanged(ClusterChangedEvent event) {
                SnapshotsInProgress snapshots = event.state().custom(SnapshotsInProgress.TYPE);
                if (snapshots != null && snapshots.entries().size() > 0) {
                    if (snapshots.entries().get(0).state() == SnapshotsInProgress.State.INIT) {
                        // The snapshot started, we can start disruption so the INIT state will arrive to another master node
                        logger.info("--> starting disruption");
                        networkDisruption.startDisrupting();
                        clusterService.removeListener(this);
                        disruptionStarted.countDown();
                    }
                }
            }

}