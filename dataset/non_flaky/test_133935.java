class DummyClass_133935 {
    @Test
    public void testCommit() {
        ResponseMsg responseACK = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getCommitLayoutResponseMsg(true)
        );

        ResponseMsg responseNACK = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.CHECK),
                getCommitLayoutResponseMsg(false)
        );

        layoutHandler.handleMessage(responseACK, mockChannelHandlerContext);
        layoutHandler.handleMessage(responseNACK, mockChannelHandlerContext);

        // Verify that the correct request was completed (once) with the appropriate value,
        // and that we did not complete exceptionally.
        verify(mockClientRouter, never()).completeExceptionally(anyLong(), any(Throwable.class));
        verify(mockClientRouter).completeRequest(responseACK.getHeader().getRequestId(), true);
        verify(mockClientRouter).completeRequest(responseNACK.getHeader().getRequestId(), false);
    }

}