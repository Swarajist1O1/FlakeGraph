class DummyClass_133957 {
    @Test
    public void testBootstrapSequencerResponse() {
        ResponseMsg responseAck = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getBootstrapSequencerResponseMsg(true)
        );
        ResponseMsg responseNack = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.CHECK),
                getBootstrapSequencerResponseMsg(false)
        );

        sequencerHandler.handleMessage(responseAck, mockChannelHandlerContext);
        sequencerHandler.handleMessage(responseNack, mockChannelHandlerContext);
        // Verify that the correct request was completed with the appropriate value,
        // and that we did not complete exceptionally.
        verify(mockClientRouter, never()).completeExceptionally(anyLong(), any(Throwable.class));
        verify(mockClientRouter).completeRequest(responseAck.getHeader().getRequestId(), true);
        verify(mockClientRouter).completeRequest(responseNack.getHeader().getRequestId(), false);
    }

}