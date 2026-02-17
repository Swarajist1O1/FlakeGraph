class DummyClass_77469 {
    @TestLogging(reason="ensure logging happens", value="org.opensearch.discovery.HandshakingTransportAddressConnector:INFO")
    public void testLogsFullConnectionFailureAfterSuccessfulHandshake() throws Exception {

        remoteNode = new DiscoveryNode("remote-node", buildNewFakeTransportAddress(), Version.CURRENT);
        remoteClusterName = "local-cluster";
        discoveryAddress = buildNewFakeTransportAddress();

        fullConnectionFailure = new ConnectTransportException(remoteNode, "simulated", new OpenSearchException("root cause"));

        FailureListener failureListener = new FailureListener();

        MockLogAppender mockAppender = new MockLogAppender();
        mockAppender.start();
        mockAppender.addExpectation(
            new MockLogAppender.SeenEventExpectation(
                "message",
                HandshakingTransportAddressConnector.class.getCanonicalName(),
                Level.WARN,
                "*completed handshake with [*] but followup connection failed*"));
        Logger targetLogger = LogManager.getLogger(HandshakingTransportAddressConnector.class);
        Loggers.addAppender(targetLogger, mockAppender);

        try {
            handshakingTransportAddressConnector.connectToRemoteMasterNode(discoveryAddress, failureListener);
            failureListener.assertFailure();
            mockAppender.assertAllExpectationsMatched();
        } finally {
            Loggers.removeAppender(targetLogger, mockAppender);
            mockAppender.stop();
        }
    }

}