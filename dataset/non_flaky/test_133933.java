class DummyClass_133933 {
    @Test
    public void testPrepare() throws IOException {
        Layout defaultLayout = getDefaultLayout();
        long defaultRank = 5L;
        ResponseMsg responseACK = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getPrepareLayoutResponseMsg(true, defaultRank, defaultLayout)
        );
        ResponseMsg responseREJECT = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.CHECK),
                getPrepareLayoutResponseMsg(false, defaultRank, defaultLayout)
        );

        // Verify that the correct request was completed (once) with the appropriate value.
        layoutHandler.handleMessage(responseACK, mockChannelHandlerContext);
        ArgumentCaptor<LayoutPrepareResponse> layoutPrepareCaptor = ArgumentCaptor.forClass(LayoutPrepareResponse.class);
        verify(mockClientRouter, never()).completeExceptionally(anyLong(), any(Throwable.class));
        verify(mockClientRouter).completeRequest(eq(responseACK.getHeader().getRequestId()), layoutPrepareCaptor.capture());

        LayoutPrepareResponse layoutPrepareCaptorValue = layoutPrepareCaptor.getValue();
        Layout retLayout = layoutPrepareCaptorValue.getLayout();
        assertLayoutMatch(retLayout);

        // Verify that the correct exception was thrown with the appropriate field set.
        layoutHandler.handleMessage(responseREJECT, mockChannelHandlerContext);
        ArgumentCaptor<OutrankedException> exceptionCaptor = ArgumentCaptor.forClass(OutrankedException.class);
        verify(mockClientRouter).completeExceptionally(
                eq(responseREJECT.getHeader().getRequestId()), exceptionCaptor.capture());
        OutrankedException outrankedException = exceptionCaptor.getValue();
        assertThat(outrankedException.getNewRank()).isEqualTo(defaultRank);

        retLayout = outrankedException.getLayout();
        assertLayoutMatch(retLayout);
    }

}