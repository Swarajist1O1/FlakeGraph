class DummyClass_133982 {
    @Test
    public void testResponsePassedAfterHandshake() {
        // Take out the handshake request message upon channelActive.
        Object out = embeddedChannel.readOutbound();
        assertTrue(out instanceof RequestMsg);
        assertTrue(((RequestMsg) out).getPayload().hasHandshakeRequest());
        // Get a HandshakeRequestMsg with specified server node id.
        ResponseMsg handshakeResponse = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getHandshakeResponseMsg(SERVER_NODEID)
        );
        // Get a ping ResponseMsg
        ResponseMsg pingResponse = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getPingResponseMsg()
        );

        embeddedChannel.writeInbound(handshakeResponse);
        embeddedChannel.writeInbound(pingResponse);

        // Verify that the ping response is passed to next handler.
        Object in = embeddedChannel.readInbound();
        assertEquals(in, pingResponse);
        // Verify that there is no outbound messages.
        assertNull(embeddedChannel.readOutbound());
    }

}