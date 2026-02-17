class DummyClass_91481 {
    @TestLogging("org.elasticsearch.snapshots:TRACE")
    public void testAbortedSnapshotDuringInitDoesNotStart() throws Exception {
        final Client client = client();

        // Blocks on initialization
        assertAcked(client.admin().cluster().preparePutRepository("repository")
            .setType("mock").setSettings(Settings.builder()
                .put("location", randomRepoPath())
                .put("block_on_init", true)
            ));

        createIndex("test-idx");
        final int nbDocs = scaledRandomIntBetween(100, 500);
        for (int i = 0; i < nbDocs; i++) {
            index("test-idx", "_doc", Integer.toString(i), "foo", "bar" + i);
        }
        flushAndRefresh("test-idx");
        assertThat(client.prepareSearch("test-idx").setSize(0).get().getHits().getTotalHits(), equalTo((long) nbDocs));

        // Create a snapshot
        client.admin().cluster().prepareCreateSnapshot("repository", "snap").execute();
        waitForBlock(internalCluster().getMasterName(), "repository", TimeValue.timeValueMinutes(1));
        boolean blocked = true;

        // Snapshot is initializing (and is blocked at this stage)
        SnapshotsStatusResponse snapshotsStatus = client.admin().cluster().prepareSnapshotStatus("repository").setSnapshots("snap").get();
        assertThat(snapshotsStatus.getSnapshots().iterator().next().getState(), equalTo(State.INIT));

        final List<State> states = new CopyOnWriteArrayList<>();
        final ClusterStateListener listener = event -> {
            SnapshotsInProgress snapshotsInProgress = event.state().custom(SnapshotsInProgress.TYPE);
            for (SnapshotsInProgress.Entry entry : snapshotsInProgress.entries()) {
                if ("snap".equals(entry.snapshot().getSnapshotId().getName())) {
                    states.add(entry.state());
                }
            }
        };

        try {
            // Record the upcoming states of the snapshot on all nodes
            internalCluster().getInstances(ClusterService.class).forEach(clusterService -> clusterService.addListener(listener));

            // Delete the snapshot while it is being initialized
            ActionFuture<AcknowledgedResponse> delete = client.admin().cluster().prepareDeleteSnapshot("repository", "snap").execute();

            // The deletion must set the snapshot in the ABORTED state
            assertBusy(() -> {
                SnapshotsStatusResponse status = client.admin().cluster().prepareSnapshotStatus("repository").setSnapshots("snap").get();
                assertThat(status.getSnapshots().iterator().next().getState(), equalTo(State.ABORTED));
            });

            // Now unblock the repository
            unblockNode("repository", internalCluster().getMasterName());
            blocked = false;

            assertAcked(delete.get());
            expectThrows(SnapshotMissingException.class, () ->
                client.admin().cluster().prepareGetSnapshots("repository").setSnapshots("snap").get());

            assertFalse("Expecting snapshot state to be updated", states.isEmpty());
            assertFalse("Expecting snapshot to be aborted and not started at all", states.contains(State.STARTED));
        } finally {
            internalCluster().getInstances(ClusterService.class).forEach(clusterService -> clusterService.removeListener(listener));
            if (blocked) {
                unblockNode("repository", internalCluster().getMasterName());
            }
        }
    }

}