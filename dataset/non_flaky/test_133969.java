class DummyClass_133969 {
    @Test
    public void testGetLogAddressSpace() {
        StreamsAddressResponse addressResponse = new StreamsAddressResponse(0L, new HashMap<>());
        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.CHECK),
                getLogAddressSpaceResponseMsg(addressResponse.getLogTail(), addressResponse.getEpoch(),
                        addressResponse.getAddressMap())
        );

        logUnitHandler.handleMessage(response, mockChannelHandlerContext);
        // Verify that the correct request was completed (once) with the appropriate value,
        // and that we did not complete exceptionally.
        verify(mockClientRouter, never()).completeExceptionally(anyLong(), any(Throwable.class));
        verify(mockClientRouter).completeRequest(response.getHeader().getRequestId(), addressResponse);
    }

}