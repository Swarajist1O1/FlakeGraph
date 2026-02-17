class DummyClass_134025 {
    @Test
    public void testRequestPassedAfterHandshake() {
        // Get a HandshakeRequestMsg with specified server node id.
        RequestMsg handshakeRequest = getRequestMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getHandshakeRequestMsg(DEFAULT_UUID, SERVER_NODEID)
        );
        // Get a ping RequestMsg
        RequestMsg pingRequest = getRequestMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getPingRequestMsg()
        );

        embeddedChannel.writeInbound(handshakeRequest);
        embeddedChannel.writeInbound(pingRequest);

        Object in = embeddedChannel.readInbound();
        Object out = embeddedChannel.readOutbound();

        // Verify that the ping request is passed to next handler.
        assertEquals(in, pingRequest);
        // Verify that the handshake is complete and HandshakeResponse is sent back.
        assertTrue(out instanceof ResponseMsg);
        assertEquals(SERVER_NODEID, getUUID(((ResponseMsg) out).getPayload().getHandshakeResponse()
                .getServerId()));
    }

}