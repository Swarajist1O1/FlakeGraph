class DummyClass_122638 {
    @Test
    public void testAlreadyInstalled() {
        terminal.expectCommand(
                "yum install --assumeyes --enablerepo=repo1 --enablerepo=repo2 --setopt skip_missing_names_on_install=False package-1 package-2 2>&1",
                0,
                "foobar\nNothing to do\n");

        assertFalse(yum
                .install("package-1", "package-2")
                .enableRepo("repo1", "repo2")
                .converge(taskContext));
    }

}