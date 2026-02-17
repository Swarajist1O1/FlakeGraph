class DummyClass_122635 {
    @Test
    public void testQueryInstalledNevra() {
        terminal.expectCommand(
                "rpm -q docker --queryformat \"%{NAME}\\\\n%{EPOCH}\\\\n%{VERSION}\\\\n%{RELEASE}\\\\n%{ARCH}\" 2>&1",
                0,
                "docker\n2\n1.13.1\n74.git6e3bb8e.el7.centos\nx86_64");

        Optional<YumPackageName> installed = yum.queryInstalled(taskContext, "docker");

        assertTrue(installed.isPresent());
        assertEquals("docker", installed.get().getName());
        assertEquals("2", installed.get().getEpoch().get());
        assertEquals("1.13.1", installed.get().getVersion().get());
        assertEquals("74.git6e3bb8e.el7.centos", installed.get().getRelease().get());
        assertEquals("x86_64", installed.get().getArchitecture().get());
    }

}