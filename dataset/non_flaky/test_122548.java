class DummyClass_122548 {
    @Test
    public void restart() {
        terminal.expectCommand("systemctl restart docker 2>&1", 0, "");
        assertTrue(new SystemCtl(terminal).restart("docker").converge(taskContext));
    }

}