class DummyClass_122547 {
    @Test
    public void stop() {
        terminal.expectCommand(
                        "systemctl show docker 2>&1",
                        0,
                        "a=b\n" +
                                "ActiveState=active\n" +
                                "bar=zoo\n")
                .expectCommand("systemctl stop docker 2>&1", 0, "");

        assertTrue(new SystemCtl(terminal).stop("docker").converge(taskContext));
    }

}