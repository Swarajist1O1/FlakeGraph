class DummyClass_122546 {
    @Test
    public void disable() {
        terminal.expectCommand("systemctl --quiet is-enabled docker 2>&1")
                .expectCommand("systemctl disable docker 2>&1")
                .expectCommand("systemctl --quiet is-enabled docker 2>&1", 1, "");

        assertTrue(new SystemCtl(terminal).disable("docker").converge(taskContext));
        assertFalse("Already converged", new SystemCtl(terminal).disable("docker").converge(taskContext));
    }

}