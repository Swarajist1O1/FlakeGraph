class DummyClass_122544 {
    @Test
    public void startIsNoop() {
        terminal.expectCommand(
                        "systemctl show docker 2>&1",
                        0,
                        "a=b\n" +
                                "ActiveState=active\n" +
                                "bar=zoo\n")
                .expectCommand("systemctl start docker 2>&1", 0, "");

        SystemCtl.SystemCtlStart startDockerService = new SystemCtl(terminal).start("docker");
        assertFalse(startDockerService.converge(taskContext));
    }

}