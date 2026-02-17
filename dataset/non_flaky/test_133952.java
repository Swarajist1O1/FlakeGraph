class DummyClass_133952 {
    @Test
    public void testHandleBootstrappedError() {
        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getBootstrappedErrorMsg()
        );

        baseHandler.handleMessage(response, mockChannelHandlerContext);

        // Verify that the correct request was completed exceptionally (once)
        // with the expected exception
        verify(mockClientRouter, never()).completeRequest(anyLong(), any());
        verify(mockClientRouter).completeExceptionally(
                eq(response.getHeader().getRequestId()), any(AlreadyBootstrappedException.class));
    }

}