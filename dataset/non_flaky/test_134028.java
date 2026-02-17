class DummyClass_134028 {
    @Test
    public void testAddNodeRequestWithoutExisting() {
        // We expect a new workflow to be created and prepare the required mocked behaviour.
        ArgumentCaptor<AddNodeRequest> requestArgumentCaptor = ArgumentCaptor.forClass(AddNodeRequest.class);
        AddNodeWorkflow mockWorkflow = mock(AddNodeWorkflow.class);
        doReturn(WORKFLOW_ID_2).when(mockWorkflow).getId();
        doReturn(mockWorkflow).when(workflowFactory).getAddNode(any(AddNodeRequest.class));

        sendAndValidateWorkflowDispatch(getAddNodeRequestMsg(ENDPOINT_2), WORKFLOW_ID_2);

        // Verify that a single AddNodeWorkflow was built for the given endpoint, and
        // that the corresponding workflowId was added to the activeWorkflows map.
        verify(workflowFactory).getAddNode(requestArgumentCaptor.capture());
        assertEquals(ENDPOINT_2, requestArgumentCaptor.getValue().getEndpoint());
        assertTrue(orchestrator.activeWorkflows.containsKey(WORKFLOW_ID_2));

        // Verify that run() was invoked with the newly created workflow.
        verify(orchestrator).run(eq(mockWorkflow), anyInt());
    }

}