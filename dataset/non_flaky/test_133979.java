class DummyClass_133979 {
    @Test
    public void testFireHandshakeSucceeded() throws Exception {
        // Get a HandshakeRequestMsg with specified server node id.
        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getHandshakeResponseMsg(SERVER_NODEID)
        );

        when(mockChannelContext.pipeline()).thenReturn(mockChannelPipeline);
        when(mockChannelPipeline.remove("readTimeoutHandler")).thenReturn(clientHandshakeHandler);

        clientHandshakeHandler.channelRead(mockChannelContext, response);

        verify(mockChannelContext).fireUserEventTriggered(ClientHandshakeEvent.CONNECTED);
    }

}