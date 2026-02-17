class DummyClass_133951 {
    @Test
    public void testHandleNotReadyError() {
        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getNotReadyErrorMsg()
        );

        baseHandler.handleMessage(response, mockChannelHandlerContext);

        // Verify that the correct request was completed exceptionally (once)
        // with the expected exception
        verify(mockClientRouter, never()).completeRequest(anyLong(), any());
        verify(mockClientRouter).completeExceptionally(
                eq(response.getHeader().getRequestId()), any(ServerNotReadyException.class));
    }

}