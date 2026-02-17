class DummyClass_134026 {
    @Test
    public void testQueryWorkflow() {
        // First perform a QUERY request for an inactive workflow.
        RequestMsg request = getRequestMsg(getBasicHeader(), getQueryWorkflowRequestMsg(WORKFLOW_ID_2));
        ArgumentCaptor<ResponseMsg> responseCaptor = ArgumentCaptor.forClass(ResponseMsg.class);
        orchestrator.handle(request, mockChannelHandlerContext, mockServerRouter);

        verify(mockServerRouter).sendResponse(responseCaptor.capture(), eq(mockChannelHandlerContext));
        ResponseMsg response = responseCaptor.getValue();

        // Assert that the payload has an ORCHESTRATOR_RESPONSE, that the base
        // header fields have remained the same, and that the queried workflow
        // was inactive.
        assertTrue(compareBaseHeaderFields(request.getHeader(), response.getHeader()));
        assertTrue(response.getPayload().hasOrchestratorResponse());
        assertTrue(response.getPayload().getOrchestratorResponse().hasQueryResult());
        assertFalse(response.getPayload().getOrchestratorResponse().getQueryResult().getActive());

        // Now perform a QUERY request for an active workflow.
        request = getRequestMsg(getBasicHeader(), getQueryWorkflowRequestMsg(WORKFLOW_ID_1));
        orchestrator.handle(request, mockChannelHandlerContext, mockServerRouter);

        verify(mockServerRouter, times(2))
                .sendResponse(responseCaptor.capture(), eq(mockChannelHandlerContext));
        response = responseCaptor.getValue();

        // Assert that the payload has an ORCHESTRATOR_RESPONSE, that the base
        // header fields have remained the same, and that the queried workflow
        // was active.
        assertTrue(compareBaseHeaderFields(request.getHeader(), response.getHeader()));
        assertTrue(response.getPayload().hasOrchestratorResponse());
        assertTrue(response.getPayload().getOrchestratorResponse().hasQueryResult());
        assertTrue(response.getPayload().getOrchestratorResponse().getQueryResult().getActive());
    }

}