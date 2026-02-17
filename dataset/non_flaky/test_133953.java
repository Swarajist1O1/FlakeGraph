class DummyClass_133953 {
    @Test
    public void testHandleNotBootstrappedError() {
        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getNotBootstrappedErrorMsg()
        );

        baseHandler.handleMessage(response, mockChannelHandlerContext);

        // Verify that the correct request was completed exceptionally (once)
        // with the expected exception
        verify(mockClientRouter, never()).completeRequest(anyLong(), any());
        verify(mockClientRouter).completeExceptionally(
                eq(response.getHeader().getRequestId()), any(NoBootstrapException.class));
    }

}