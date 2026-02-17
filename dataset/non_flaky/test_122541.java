class DummyClass_122541 {
    @Test
    public void enable() {
        terminal.expectCommand("systemctl --quiet is-enabled docker 2>&1", 1, "")
                .expectCommand("systemctl enable docker 2>&1")
                .expectCommand("systemctl --quiet is-enabled docker 2>&1");

        SystemCtl.SystemCtlEnable enableDockerService = new SystemCtl(terminal).enable("docker");
        assertTrue(enableDockerService.converge(taskContext));
        assertFalse("Already converged", enableDockerService.converge(taskContext));
    }

}