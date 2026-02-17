class DummyClass_134023 {
    @Test
    public void testVersionMismatchHandshakeSucceed() {
        // Get a HandshakeRequestMsg whose corfu_source_code_version set in the header is different
        // from that at server side.
        RequestMsg request = getRequestMsg(
            HeaderMsg.newBuilder()
                .setVersion(
                     ProtocolVersionMsg.newBuilder()
                    .setCorfuSourceCodeVersion(FAKE_CLIENT_VERSION)
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
            getHandshakeRequestMsg(DEFAULT_UUID, SERVER_NODEID)
        );

        embeddedChannel.writeInbound(request);
        Object out = embeddedChannel.readOutbound();

        // Verify that the handshake could still complete even if the versions of client and server
        // are different.
        assertTrue(out instanceof ResponseMsg);
        assertEquals(SERVER_NODEID, getUUID(((ResponseMsg) out).getPayload().getHandshakeResponse()
                .getServerId()));
    }

}