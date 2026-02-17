class DummyClass_133981 {
    @Test
    public void testResponseDroppedBeforeHandshake() {
        // Take out the handshake request message upon channelActive.
        Object out = embeddedChannel.readOutbound();
        assertTrue(out instanceof RequestMsg);
        assertTrue(((RequestMsg) out).getPayload().hasHandshakeRequest());
        // Get a ping ResponseMsg
        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getPingResponseMsg()
        );

        embeddedChannel.writeInbound(response);

        // Verify that the response was correctly dropped and there is no inbound nor outbound messages.
        assertNull(embeddedChannel.readInbound());
        assertNull(embeddedChannel.readOutbound());
    }

}