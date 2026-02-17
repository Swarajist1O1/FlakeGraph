class DummyClass_133965 {
    @Test
    public void testInspectAddresses() {
        List<Long> emptyAddresses = new ArrayList<>();
        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.CHECK, EpochCheck.CHECK),
                getInspectAddressesResponseMsg(emptyAddresses)
        );

        ArgumentCaptor<InspectAddressesResponse> captor = ArgumentCaptor.forClass(InspectAddressesResponse.class);

        logUnitHandler.handleMessage(response, mockChannelHandlerContext);
        // Verify that the correct request was completed (once) with the appropriate value,
        // and that we did not complete exceptionally.
        verify(mockClientRouter, never()).completeExceptionally(anyLong(), any(Throwable.class));
        verify(mockClientRouter).completeRequest(eq(response.getHeader().getRequestId()), captor.capture());
        assertEquals(emptyAddresses, captor.getValue().getEmptyAddresses());
    }

}