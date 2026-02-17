class DummyClass_133944 {
    @Test
    public void testHandleOrchestrator() {
        // Test with an ORCHESTRATOR_RESPONSE of type QueryResponse.
        ResponseMsg response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.IGNORE, EpochCheck.IGNORE),
                getQueriedWorkflowResponseMsg(true)
        );

        ArgumentCaptor<QueryResponse> qrCaptor = ArgumentCaptor.forClass(QueryResponse.class);
        managementHandler.handleMessage(response, mockChannelHandlerContext);

        // Verify that the correct request was completed (once) with the appropriate value,
        // and that we did not complete exceptionally.
        verify(mockClientRouter, never()).completeExceptionally(anyLong(), any(Throwable.class));
        verify(mockClientRouter).completeRequest(eq(response.getHeader().getRequestId()), qrCaptor.capture());
        assertTrue(qrCaptor.getValue().isActive());

        // Test with an ORCHESTRATOR_RESPONSE of type CreateWorkflowResponse.
        response = getResponseMsg(
                getBasicHeader(ClusterIdCheck.IGNORE, EpochCheck.IGNORE),
                getCreatedWorkflowResponseMsg(DEFAULT_UUID)
        );

        ArgumentCaptor<CreateWorkflowResponse> cwCaptor = ArgumentCaptor.forClass(CreateWorkflowResponse.class);
        managementHandler.handleMessage(response, mockChannelHandlerContext);

        // Verify that the correct request was completed (once) with the appropriate value,
        // and that we did not complete exceptionally.
        verify(mockClientRouter, never()).completeExceptionally(anyLong(), any(Throwable.class));
        verify(mockClientRouter).completeRequest(eq(response.getHeader().getRequestId()), cwCaptor.capture());
        assertEquals(DEFAULT_UUID, cwCaptor.getValue().workflowId);
    }

}