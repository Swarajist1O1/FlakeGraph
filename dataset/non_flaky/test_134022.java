class DummyClass_134022 {
    @Test
    public void testHandshakeSucceed() {
        // Get a HandshakeRequestMsg with specified server node id.
        RequestMsg request = getRequestMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getHandshakeRequestMsg(DEFAULT_UUID, SERVER_NODEID)
        );

        embeddedChannel.writeInbound(request);
        Object out = embeddedChannel.readOutbound();

        // Verify that the handshake is complete and HandshakeResponse is sent back.
        assertTrue(out instanceof ResponseMsg);
        assertEquals(SERVER_NODEID, getUUID(((ResponseMsg) out).getPayload().getHandshakeResponse()
                .getServerId()));
    }

}