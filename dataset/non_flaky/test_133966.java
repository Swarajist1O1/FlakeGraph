class DummyClass_133966 {
    @Test
    public void testTailResponse() {
        TailsResponse sampleTailsResponse = new TailsResponse(0L, 0L, new HashMap<>());
        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.CHECK),
                getTailResponseMsg(sampleTailsResponse.getEpoch(), sampleTailsResponse.getLogTail(),
                        sampleTailsResponse.getStreamTails())
        );

        logUnitHandler.handleMessage(response, mockChannelHandlerContext);
        // Verify that the correct request was completed (once) with the appropriate value,
        // and that we did not complete exceptionally.
        verify(mockClientRouter, never()).completeExceptionally(anyLong(), any(Throwable.class));
        verify(mockClientRouter).completeRequest(response.getHeader().getRequestId(), sampleTailsResponse);
    }

}