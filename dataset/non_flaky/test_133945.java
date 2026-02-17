class DummyClass_133945 {
    @Test
    public void testHandlePing() {
        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getPingResponseMsg()
        );

        baseHandler.handleMessage(response, mockChannelHandlerContext);

        // Verify that the correct request was completed (once) with the appropriate value,
        // and that we did not complete exceptionally.
        verify(mockClientRouter, never()).completeExceptionally(anyLong(), any(Throwable.class));
        verify(mockClientRouter).completeRequest(response.getHeader().getRequestId(), true);
    }

}