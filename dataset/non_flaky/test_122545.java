class DummyClass_122545 {
    @Test
    public void startCommandFailre() {
        terminal.expectCommand("systemctl show docker 2>&1", 1, "error");
        SystemCtl.SystemCtlStart startDockerService = new SystemCtl(terminal).start("docker");
        try {
            startDockerService.converge(taskContext);
            fail();
        } catch (ChildProcessFailureException e) {
            // success
        }
    }

}