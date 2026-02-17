class DummyClass_133971 {
    @Test
    public void testRequestKnownAddresses() {
        KnownAddressResponse knownAddressResponse = new KnownAddressResponse(new HashSet<>());
        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.CHECK),
                getKnownAddressResponseMsg(knownAddressResponse.getKnownAddresses())
        );

        logUnitHandler.handleMessage(response, mockChannelHandlerContext);
        // Verify that the correct request was completed (once) with the appropriate value,
        // and that we did not complete exceptionally.
        verify(mockClientRouter, never()).completeExceptionally(anyLong(), any(Throwable.class));
        verify(mockClientRouter).completeRequest(response.getHeader().getRequestId(), knownAddressResponse);
    }

}