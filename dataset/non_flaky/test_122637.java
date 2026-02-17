class DummyClass_122637 {
    @Test
    public void testQueryNotInstalled() {
        terminal.expectCommand(
                "rpm -q fake-package --queryformat \"%{NAME}\\\\n%{EPOCH}\\\\n%{VERSION}\\\\n%{RELEASE}\\\\n%{ARCH}\" 2>&1",
                1,
                "package fake-package is not installed");

        Optional<YumPackageName> installed = yum.queryInstalled(taskContext, "fake-package");

        assertFalse(installed.isPresent());
    }

}