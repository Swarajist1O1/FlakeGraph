class DummyClass_91489 {
    @TestLogging("_root:DEBUG,org.elasticsearch.indices.recovery:TRACE")
    public void testDisconnectsDuringRecovery() throws Exception {
        boolean primaryRelocation = randomBoolean();
        final String indexName = "test";
        final Settings nodeSettings = Settings.builder()
            .put(RecoverySettings.INDICES_RECOVERY_RETRY_DELAY_NETWORK_SETTING.getKey(), TimeValue.timeValueMillis(randomIntBetween(0, 100)))
            .build();
        TimeValue disconnectAfterDelay = TimeValue.timeValueMillis(randomIntBetween(0, 100));
        // start a master node
        String masterNodeName = internalCluster().startMasterOnlyNode(nodeSettings);

        final String blueNodeName = internalCluster().startNode(Settings.builder().put("node.attr.color", "blue").put(nodeSettings).build());
        final String redNodeName = internalCluster().startNode(Settings.builder().put("node.attr.color", "red").put(nodeSettings).build());

        client().admin().indices().prepareCreate(indexName)
            .setSettings(
                Settings.builder()
                    .put(IndexMetaData.INDEX_ROUTING_INCLUDE_GROUP_SETTING.getKey() + "color", "blue")
                    .put(IndexMetaData.SETTING_NUMBER_OF_SHARDS, 1)
                    .put(IndexMetaData.SETTING_NUMBER_OF_REPLICAS, 0)
            ).get();

        List<IndexRequestBuilder> requests = new ArrayList<>();
        int numDocs = scaledRandomIntBetween(25, 250);
        for (int i = 0; i < numDocs; i++) {
            requests.add(client().prepareIndex(indexName, "type").setSource("{}", XContentType.JSON));
        }
        indexRandom(true, requests);
        ensureSearchable(indexName);
        assertHitCount(client().prepareSearch(indexName).get(), numDocs);

        MockTransportService masterTransportService = (MockTransportService) internalCluster().getInstance(TransportService.class, masterNodeName);
        MockTransportService blueMockTransportService = (MockTransportService) internalCluster().getInstance(TransportService.class, blueNodeName);
        MockTransportService redMockTransportService = (MockTransportService) internalCluster().getInstance(TransportService.class, redNodeName);

        redMockTransportService.addSendBehavior(blueMockTransportService, new StubbableTransport.SendRequestBehavior() {
            private final AtomicInteger count = new AtomicInteger();

            @Override
            public void sendRequest(Transport.Connection connection, long requestId, String action, TransportRequest request,
                                    TransportRequestOptions options) throws IOException {
                logger.info("--> sending request {} on {}", action, connection.getNode());
                if (PeerRecoverySourceService.Actions.START_RECOVERY.equals(action) && count.incrementAndGet() == 1) {
                    // ensures that it's considered as valid recovery attempt by source
                    try {
                        awaitBusy(() -> client(blueNodeName).admin().cluster().prepareState().setLocal(true).get()
                            .getState().getRoutingTable().index("test").shard(0).getAllInitializingShards().isEmpty() == false);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    connection.sendRequest(requestId, action, request, options);
                    try {
                        Thread.sleep(disconnectAfterDelay.millis());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    throw new ConnectTransportException(connection.getNode(), "DISCONNECT: simulation disconnect after successfully sending " + action + " request");
                } else {
                    connection.sendRequest(requestId, action, request, options);
                }
            }

}