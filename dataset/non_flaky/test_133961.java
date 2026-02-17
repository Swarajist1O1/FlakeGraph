class DummyClass_133961 {
    @Test
    public void testStreamsAddressResponseDefaultAddressMap() {
        long defaultLogTail = 5L;
        long defaultEpoch = 10L;
        Map<UUID, StreamAddressSpace> defaultMap = getDefaultAddressMap();

        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.CHECK),
                getStreamsAddressResponseMsg(defaultLogTail, defaultEpoch, defaultMap)
        );

        sequencerHandler.handleMessage(response, mockChannelHandlerContext);
        ArgumentCaptor<StreamsAddressResponse> captor = ArgumentCaptor.forClass(StreamsAddressResponse.class);
        // Verify that the correct request was completed (once) with the appropriate value,
        // and that we did not complete exceptionally.
        verify(mockClientRouter, never()).completeExceptionally(anyLong(), any(Throwable.class));
        verify(mockClientRouter).completeRequest(eq(response.getHeader().getRequestId()), captor.capture());

        StreamsAddressResponse streamsAddressResponse = captor.getValue();
        assertEquals(defaultLogTail, streamsAddressResponse.getLogTail());
        assertEquals(defaultEpoch, streamsAddressResponse.getEpoch());
        Map<UUID, StreamAddressSpace> retMap = streamsAddressResponse.getAddressMap();
        assertEquals(retMap.size(), defaultMap.size());
        for (UUID id : defaultMap.keySet()) {
            assertEquals(defaultMap.get(id).toString(), retMap.get(id).toString());
        }
    }

}