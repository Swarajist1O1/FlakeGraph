class DummyClass_133949 {
    @Test
    public void testHandleWrongEpochError() {
        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getWrongEpochErrorMsg(2L)
        );

        ArgumentCaptor<WrongEpochException> exceptionCaptor = ArgumentCaptor.forClass(WrongEpochException.class);
        baseHandler.handleMessage(response, mockChannelHandlerContext);

        // Verify that the correct request was completed exceptionally (once)
        // with the expected exception
        verify(mockClientRouter, never()).completeRequest(anyLong(), any());
        verify(mockClientRouter).completeExceptionally(
                eq(response.getHeader().getRequestId()), exceptionCaptor.capture());

        assertEquals(2L, exceptionCaptor.getValue().getCorrectEpoch());
    }

}