class DummyClass_133930 {
    @Test
    public void testGetLayout() throws IOException {
        Layout defaultLayout = getDefaultLayout();

        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getLayoutResponseMsg(defaultLayout)
        );

        layoutHandler.handleMessage(response, mockChannelHandlerContext);
        // Verify that the correct request was completed (once) with the appropriate value,
        // and that we did not complete exceptionally.
        verify(mockClientRouter, never()).completeExceptionally(anyLong(), any(Throwable.class));
        verify(mockClientRouter).completeRequest(response.getHeader().getRequestId(), defaultLayout);
    }

}