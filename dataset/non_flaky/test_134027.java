class DummyClass_134027 {
    @Test
    public void testAddNodeRequestWithExisting() {
        sendAndValidateWorkflowDispatch(getAddNodeRequestMsg(ENDPOINT_1), WORKFLOW_ID_1);

        // Verify that no new workflow is run.
        verify(orchestrator, never()).run(any(IWorkflow.class), anyInt());
    }

}