class DummyClass_133973 {
    @Test
    public void testCompact() {
        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.CHECK),
                getCompactResponseMsg()
        );

        logUnitHandler.handleMessage(response, mockChannelHandlerContext);
        // Verify that the correct request was completed (once) with the appropriate value,
        // and that we did not complete exceptionally.
        verify(mockClientRouter, never()).completeExceptionally(anyLong(), any(Throwable.class));
        verify(mockClientRouter).completeRequest(response.getHeader().getRequestId(), true);
    }

}