class DummyClass_91463 {
@TestLogging("_root:DEBUG")
    public void testNoShardRelocationsOccurWhenElectedMasterNodeFails() throws Exception {
        Settings defaultSettings = Settings.builder()
                .put(FaultDetection.PING_TIMEOUT_SETTING.getKey(), "1s")
                .put(FaultDetection.PING_RETRIES_SETTING.getKey(), "1")
                .build();

        Settings masterNodeSettings = Settings.builder()
                .put(Node.NODE_DATA_SETTING.getKey(), false)
                .put(defaultSettings)
                .build();
        internalCluster().startNodes(2, masterNodeSettings);
        Settings dateNodeSettings = Settings.builder()
                .put(Node.NODE_MASTER_SETTING.getKey(), false)
                .put(defaultSettings)
                .build();
        internalCluster().startNodes(2, dateNodeSettings);
        ClusterHealthResponse clusterHealthResponse = client().admin().cluster().prepareHealth()
                .setWaitForEvents(Priority.LANGUID)
                .setWaitForNodes("4")
                .setWaitForNoRelocatingShards(true)
                .get();
        assertThat(clusterHealthResponse.isTimedOut(), is(false));

        createIndex("test");
        ensureSearchable("test");
        RecoveryResponse r = client().admin().indices().prepareRecoveries("test").get();
        int numRecoveriesBeforeNewMaster = r.shardRecoveryStates().get("test").size();

        final String oldMaster = internalCluster().getMasterName();
        internalCluster().stopCurrentMasterNode();
        assertBusy(() -> {
            String current = internalCluster().getMasterName();
            assertThat(current, notNullValue());
            assertThat(current, not(equalTo(oldMaster)));
        });
        ensureSearchable("test");

        r = client().admin().indices().prepareRecoveries("test").get();
        int numRecoveriesAfterNewMaster = r.shardRecoveryStates().get("test").size();
        assertThat(numRecoveriesAfterNewMaster, equalTo(numRecoveriesBeforeNewMaster));
    }

}