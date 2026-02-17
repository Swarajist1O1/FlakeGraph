class DummyClass_77482 {
    @TestLogging(
    public void testEnsureWeReconnect() throws Exception {
        Settings remoteSettings = Settings.builder().put(ClusterName.CLUSTER_NAME_SETTING.getKey(), "foo_bar_cluster").build();
        try (MockTransportService remoteTransport = startTransport("remote_node", Collections.emptyList(), Version.CURRENT, threadPool,
            remoteSettings)) {
            DiscoveryNode remoteNode = remoteTransport.getLocalDiscoNode();
            Settings localSettings = Settings.builder()
                .put(onlyRole(DiscoveryNodeRole.REMOTE_CLUSTER_CLIENT_ROLE))
                .put("cluster.remote.test.seeds",
                    remoteNode.getAddress().getAddress() + ":" + remoteNode.getAddress().getPort()).build();
            try (MockTransportService service = MockTransportService.createNewService(localSettings, Version.CURRENT, threadPool, null)) {
                service.start();
                // this test is not perfect since we might reconnect concurrently but it will fail most of the time if we don't have
                // the right calls in place in the RemoteAwareClient
                service.acceptIncomingRequests();
                RemoteClusterService remoteClusterService = service.getRemoteClusterService();
                assertBusy(() -> assertTrue(remoteClusterService.isRemoteNodeConnected("test", remoteNode)));
                for (int i = 0; i < 10; i++) {
                    RemoteClusterConnection remoteClusterConnection = remoteClusterService.getRemoteClusterConnection("test");
                    assertBusy(remoteClusterConnection::assertNoRunningConnections);
                    ConnectionManager connectionManager = remoteClusterConnection.getConnectionManager();
                    Transport.Connection connection = connectionManager.getConnection(remoteNode);
                    PlainActionFuture<Void> closeFuture = PlainActionFuture.newFuture();
                    connection.addCloseListener(closeFuture);
                    connectionManager.disconnectFromNode(remoteNode);
                    closeFuture.get();

                    Client client = remoteClusterService.getRemoteClusterClient(threadPool, "test");
                    ClusterStateResponse clusterStateResponse = client.admin().cluster().prepareState().execute().get();
                    assertNotNull(clusterStateResponse);
                    assertEquals("foo_bar_cluster", clusterStateResponse.getState().getClusterName().value());
                    assertTrue(remoteClusterConnection.isNodeConnected(remoteNode));
                }
            }
        }
    }

}