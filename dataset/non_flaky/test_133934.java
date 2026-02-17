class DummyClass_133934 {
    @Test
    public void testPropose() {
        long defaultRank = 5L;
        ResponseMsg responseACK = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getProposeLayoutResponseMsg(true, defaultRank)
        );
        ResponseMsg responseREJECT = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.CHECK),
                getProposeLayoutResponseMsg(false, defaultRank)
        );

        // Verify that the correct request was completed (once) with the appropriate value.
        layoutHandler.handleMessage(responseACK, mockChannelHandlerContext);
        verify(mockClientRouter, never()).completeExceptionally(anyLong(), any(Throwable.class));
        verify(mockClientRouter).completeRequest(responseACK.getHeader().getRequestId(), true);

        // Verify that the correct exception was thrown with the appropriate field set.
        layoutHandler.handleMessage(responseREJECT, mockChannelHandlerContext);
        ArgumentCaptor<OutrankedException> exceptionCaptor = ArgumentCaptor.forClass(OutrankedException.class);
        verify(mockClientRouter).completeExceptionally(
                eq(responseREJECT.getHeader().getRequestId()), exceptionCaptor.capture());
        OutrankedException outrankedException = exceptionCaptor.getValue();
        assertThat(outrankedException.getNewRank()).isEqualTo(defaultRank);
    }

}