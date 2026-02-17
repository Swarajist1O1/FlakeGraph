class DummyClass_133942 {
    @Test
    public void testHandleManagementLayout() {
        final Layout layout = getBasicLayout(ImmutableList.of("localhost:9000"));
        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getManagementLayoutResponseMsg(layout)
        );

        managementHandler.handleMessage(response, mockChannelHandlerContext);

        // Verify that the correct request was completed (once) with the appropriate value,
        // and that we did not complete exceptionally.
        verify(mockClientRouter, never()).completeExceptionally(anyLong(), any(Throwable.class));
        verify(mockClientRouter).completeRequest(response.getHeader().getRequestId(), layout);
    }

}