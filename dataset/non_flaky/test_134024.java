class DummyClass_134024 {
    @Test
    public void testRequestDroppedBeforeHandshake() {
        // Get a ping RequestMsg
        RequestMsg request = getRequestMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getPingRequestMsg()
        );

        embeddedChannel.writeInbound(request);

        // Verify that the request was correctly dropped and there is no inbound nor outbound messages.
        assertNull(embeddedChannel.readInbound());
        assertNull(embeddedChannel.readOutbound());
    }

}