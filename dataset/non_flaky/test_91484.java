class DummyClass_91484 {
    @TestLogging("_root:DEBUG")
    public void testMasterFailoverDuringIndexingWithMappingChanges() throws Throwable {
        logger.info("--> start 4 nodes, 3 master, 1 data");

        final Settings sharedSettings = Settings.builder()
                .put(FaultDetection.PING_TIMEOUT_SETTING.getKey(), "1s") // for hitting simulated network failures quickly
                .put(FaultDetection.PING_RETRIES_SETTING.getKey(), "1") // for hitting simulated network failures quickly
                .put("discovery.zen.join_timeout", "10s")  // still long to induce failures but to long so test won't time out
                .put(DiscoverySettings.PUBLISH_TIMEOUT_SETTING.getKey(), "1s") // <-- for hitting simulated network failures quickly
                .put(ElectMasterService.DISCOVERY_ZEN_MINIMUM_MASTER_NODES_SETTING.getKey(), 2)
                .build();

        internalCluster().startMasterOnlyNodes(3, sharedSettings);

        String dataNode = internalCluster().startDataOnlyNode(sharedSettings);

        logger.info("--> wait for all nodes to join the cluster");
        ensureStableCluster(4);

        // We index data with mapping changes into cluster and have master failover at same time
        client().admin().indices().prepareCreate("myindex")
                .setSettings(Settings.builder().put("index.number_of_shards", 1).put("index.number_of_replicas", 0))
                .get();
        ensureGreen("myindex");

        final CyclicBarrier barrier = new CyclicBarrier(2);

        Thread indexingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    logger.warn("Barrier interrupted", e);
                    return;
                } catch (BrokenBarrierException e) {
                    logger.warn("Broken barrier", e);
                    return;
                }
                for (int i = 0; i < 10; i++) {
                    // index data with mapping changes
                    IndexResponse response = client(dataNode).prepareIndex("myindex", "mytype").setSource("field_" + i, "val").get();
                    assertEquals(DocWriteResponse.Result.CREATED, response.getResult());
                }
            }

}