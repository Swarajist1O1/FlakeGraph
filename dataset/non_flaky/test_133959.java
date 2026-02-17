class DummyClass_133959 {
    @Test
    public void testSequencerMetricsResponseNormal() {
        SequencerMetrics sequencerMetricsReady = SequencerMetrics.READY;
        SequencerMetrics sequencerMetricsNotReady = SequencerMetrics.NOT_READY;
        SequencerMetrics sequencerMetricsUnknown = SequencerMetrics.UNKNOWN;
        ResponseMsg responseReady = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getSequencerMetricsResponseMsg(sequencerMetricsReady)
        );
        ResponseMsg responseNotReady = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getSequencerMetricsResponseMsg(sequencerMetricsNotReady)
        );
        ResponseMsg responseUnkown = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.IGNORE),
                getSequencerMetricsResponseMsg(sequencerMetricsUnknown)
        );

        sequencerHandler.handleMessage(responseReady, mockChannelHandlerContext);
        sequencerHandler.handleMessage(responseNotReady, mockChannelHandlerContext);
        sequencerHandler.handleMessage(responseUnkown, mockChannelHandlerContext);
        // Verify that the correct request was completed with the appropriate value,
        // and that we did not complete exceptionally.
        verify(mockClientRouter, never()).completeExceptionally(anyLong(), any(Throwable.class));
        verify(mockClientRouter).completeRequest(responseReady.getHeader().getRequestId(), sequencerMetricsReady);
        verify(mockClientRouter).completeRequest(responseNotReady.getHeader().getRequestId(), sequencerMetricsNotReady);
        verify(mockClientRouter).completeRequest(responseUnkown.getHeader().getRequestId(), sequencerMetricsUnknown);
    }

}