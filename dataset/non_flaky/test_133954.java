class DummyClass_133954 {
    @Test
    public void testHandleUnknownError() {
        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getUnknownErrorMsg(new Exception("Unknown Exception Test"))
        );

        ArgumentCaptor<Exception> exceptionCaptor = ArgumentCaptor.forClass(Exception.class);
        baseHandler.handleMessage(response, mockChannelHandlerContext);

        // Verify that the correct request was completed exceptionally (once)
        // with the expected exception
        verify(mockClientRouter, never()).completeRequest(anyLong(), any());
        verify(mockClientRouter).completeExceptionally(
                eq(response.getHeader().getRequestId()), exceptionCaptor.capture());

        assertEquals("Unknown Exception Test", exceptionCaptor.getValue().getMessage());
    }

}