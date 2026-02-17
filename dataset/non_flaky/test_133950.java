class DummyClass_133950 {
    @Test
    public void testHandleWrongClusterError() {
        final UUID EXPECTED_UUID = UUID.randomUUID();
        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getWrongClusterErrorMsg(getUuidMsg(EXPECTED_UUID), getUuidMsg(DEFAULT_UUID))
        );

        ArgumentCaptor<WrongClusterException> exceptionCaptor = ArgumentCaptor.forClass(WrongClusterException.class);
        baseHandler.handleMessage(response, mockChannelHandlerContext);

        // Verify that the correct request was completed exceptionally (once)
        // with the expected exception
        verify(mockClientRouter, never()).completeRequest(anyLong(), any());
        verify(mockClientRouter).completeExceptionally(
                eq(response.getHeader().getRequestId()), exceptionCaptor.capture());

        assertEquals(EXPECTED_UUID, exceptionCaptor.getValue().getExpectedCluster());
        assertEquals(DEFAULT_UUID, exceptionCaptor.getValue().getActualCluster());
    }

}