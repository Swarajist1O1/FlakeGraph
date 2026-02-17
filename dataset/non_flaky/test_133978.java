class DummyClass_133978 {
    @Test
    public void testDataCorruptionError() {
        long sampleAddress = 5L;
        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getDataCorruptionErrorMsg(sampleAddress)
        );

        logUnitHandler.handleMessage(response, mockChannelHandlerContext);
        // Verify that the correct request was completed exceptionally.
        verify(mockClientRouter, never()).completeRequest(anyLong(), any());
        verify(mockClientRouter).completeExceptionally(
                eq(response.getHeader().getRequestId()), any(DataCorruptionException.class));
    }

}