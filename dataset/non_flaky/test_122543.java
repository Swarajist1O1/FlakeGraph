class DummyClass_122543 {
    @Test
    public void start() {
        terminal.expectCommand(
                        "systemctl show docker 2>&1",
                        0,
                        "a=b\n" +
                                "ActiveState=failed\n" +
                                "bar=zoo\n")
                .expectCommand("systemctl start docker 2>&1", 0, "");

        SystemCtl.SystemCtlStart startDockerService = new SystemCtl(terminal).start("docker");
        assertTrue(startDockerService.converge(taskContext));
    }

}