class DummyClass_133977 {
    @Test
    public void testOverwriteError() {
        int causeIdWrittenByHole = OverwriteCause.SAME_DATA.getId();
        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getOverwriteErrorMsg(causeIdWrittenByHole)
        );

        logUnitHandler.handleMessage(response, mockChannelHandlerContext);
        ArgumentCaptor<OverwriteException> captor = ArgumentCaptor.forClass(OverwriteException.class);
        // Verify that the correct request was completed exceptionally.
        verify(mockClientRouter, never()).completeRequest(anyLong(), any());
        verify(mockClientRouter).completeExceptionally(
                eq(response.getHeader().getRequestId()), captor.capture());
        assertEquals(causeIdWrittenByHole, captor.getValue().getOverWriteCause().getId());
    }

}