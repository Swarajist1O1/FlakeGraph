class DummyClass_122640 {
    @Test
    public void testAlreadyRemoved() {
        terminal.expectCommand(
                "yum remove --assumeyes package-1 package-2 2>&1",
                0,
                "foobar\nNo Packages marked for removal\n");

        assertFalse(yum
                .remove("package-1", "package-2")
                .converge(taskContext));
    }

}