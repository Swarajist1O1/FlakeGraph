class DummyClass_122639 {
    @Test
    public void testAlreadyUpgraded() {
        terminal.expectCommand(
                "yum upgrade --assumeyes --setopt skip_missing_names_on_update=False package-1 package-2 2>&1",
                0,
                "foobar\nNo packages marked for update\n");

        assertFalse(yum
                .upgrade("package-1", "package-2")
                .converge(taskContext));
    }

}