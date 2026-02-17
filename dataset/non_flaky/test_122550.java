class DummyClass_122550 {
    @Test
    public void withSudo() {
        SystemCtl systemCtl = new SystemCtl(terminal).withSudo();
        terminal.expectCommand("sudo systemctl restart docker 2>&1", 0, "");
        assertTrue(systemCtl.restart("docker").converge(taskContext));
    }

}