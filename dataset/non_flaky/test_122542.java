class DummyClass_122542 {
    @Test
    public void enableCommandFailure() {
        terminal.expectCommand("systemctl --quiet is-enabled docker 2>&1", 1, "")
                .expectCommand("systemctl enable docker 2>&1", 1, "error enabling service");
        SystemCtl.SystemCtlEnable enableDockerService = new SystemCtl(terminal).enable("docker");
        try {
            enableDockerService.converge(taskContext);
            fail();
        } catch (ChildProcessFailureException e) {
            // success
        }
    }

}