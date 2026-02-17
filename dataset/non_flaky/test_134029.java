class DummyClass_134029 {
    @Test
    public void testRemoveNodeRequestWithExisting() {
        sendAndValidateWorkflowDispatch(getRemoveNodeRequestMsg(ENDPOINT_1), WORKFLOW_ID_1);

        // Verify that no new workflow is run.
        verify(orchestrator, never()).run(any(IWorkflow.class), anyInt());
    }

}