class DummyClass_133970 {
    @Test
    public void testGetTrimMark() {
        long sampleTrimMark = 5L;
        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.CHECK),
                getTrimMarkResponseMsg(sampleTrimMark)
        );

        logUnitHandler.handleMessage(response, mockChannelHandlerContext);
        // Verify that the correct request was completed (once) with the appropriate value,
        // and that we did not complete exceptionally.
        verify(mockClientRouter, never()).completeExceptionally(anyLong(), any(Throwable.class));
        verify(mockClientRouter).completeRequest(response.getHeader().getRequestId(), sampleTrimMark);
    }

}