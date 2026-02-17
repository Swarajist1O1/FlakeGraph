class DummyClass_122636 {
    @Test
    public void testQueryInstalledPartial() {
        terminal.expectCommand(
                "rpm -q vespa-node-admin --queryformat \"%{NAME}\\\\n%{EPOCH}\\\\n%{VERSION}\\\\n%{RELEASE}\\\\n%{ARCH}\" 2>&1",
                0,
                "vespa-node-admin\n(none)\n6.283.62\n1.el7\nnoarch");

        Optional<YumPackageName> installed = yum.queryInstalled(taskContext, "vespa-node-admin");

        assertTrue(installed.isPresent());
        assertEquals("vespa-node-admin", installed.get().getName());
        assertFalse(installed.get().getEpoch().isPresent());
        assertEquals("6.283.62", installed.get().getVersion().get());
        assertEquals("1.el7", installed.get().getRelease().get());
        assertEquals("noarch", installed.get().getArchitecture().get());
    }

}