class DummyClass_133976 {
    @Test
    public void testTrimmedError() {
        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getTrimmedErrorMsg()
        );

        logUnitHandler.handleMessage(response, mockChannelHandlerContext);
        // Verify that the correct request was completed exceptionally.
        verify(mockClientRouter, never()).completeRequest(anyLong(), any());
        verify(mockClientRouter).completeExceptionally(
                eq(response.getHeader().getRequestId()), any(TrimmedException.class));
    }

}