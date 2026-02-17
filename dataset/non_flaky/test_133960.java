class DummyClass_133960 {
    @Test
    public void testStreamsAddressResponseEmptyAddressMap() {
        long defaultLogTail = 5L;
        long defaultEpoch = 10L;

        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.CHECK),
                getStreamsAddressResponseMsg(defaultLogTail, defaultEpoch, Collections.emptyMap())
        );

        sequencerHandler.handleMessage(response, mockChannelHandlerContext);
        ArgumentCaptor<StreamsAddressResponse> captor = ArgumentCaptor.forClass(StreamsAddressResponse.class);
        // Verify that the correct request was completed (once) with the appropriate value,
        // and that we did not complete exceptionally.
        verify(mockClientRouter, never()).completeExceptionally(anyLong(), any(Throwable.class));
        verify(mockClientRouter).completeRequest(eq(response.getHeader().getRequestId()), captor.capture());

        StreamsAddressResponse streamsAddressResponse = captor.getValue();
        assertTrue(streamsAddressResponse.getAddressMap().isEmpty());
        assertEquals(defaultLogTail, streamsAddressResponse.getLogTail());
        assertEquals(defaultEpoch, streamsAddressResponse.getEpoch());
    }

}