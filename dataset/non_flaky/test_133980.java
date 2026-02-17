class DummyClass_133980 {
    @Test
    public void testVersionMismatchHandshakeSucceeded() throws Exception {
        // Get a HandshakeResponseMsg whose corfu_source_code_version set in the header is different
        // from that at client side.
        ResponseMsg response = getResponseMsg(
            HeaderMsg.newBuilder()
                .setVersion(
                    ProtocolVersionMsg.newBuilder()
                        .setCorfuSourceCodeVersion(FAKE_SERVER_VERSION)
                        .setCapabilityVector(CompatibilityVectorUtils.getCompatibilityVectors())
                        .build())
                .setRequestId(requestCounter.incrementAndGet())
                .setPriority(PriorityLevel.NORMAL)
                .setEpoch(0L)
                .setClusterId(getUuidMsg(DEFAULT_UUID))
                .setClientId(getUuidMsg(DEFAULT_UUID))
                .setIgnoreClusterId(false)
                .setIgnoreEpoch(true)
                .build(),
            getHandshakeResponseMsg(SERVER_NODEID)
        );

        when(mockChannelContext.pipeline()).thenReturn(mockChannelPipeline);
        when(mockChannelPipeline.remove("readTimeoutHandler")).thenReturn(clientHandshakeHandler);

        clientHandshakeHandler.channelRead(mockChannelContext, response);

        // Currently when versions do not match we do nothing but log warning, so the handshake
        // is supposed to succeed.
        verify(mockChannelContext).fireUserEventTriggered(ClientHandshakeEvent.CONNECTED);
    }

}