class DummyClass_122625 {
    @Test
    public void default_env_is_correctly_rewritten() throws IOException {
        Path tempFile = temporaryFolder.newFile().toPath();
        Files.copy(EXAMPLE_FILE, tempFile, REPLACE_EXISTING);

        DefaultEnvWriter writer = new DefaultEnvWriter();
        writer.addOverride("VESPA_HOSTNAME", "my-new-hostname");
        writer.addFallback("VESPA_CONFIGSERVER", "new-fallback-configserver");
        writer.addOverride("VESPA_TLS_CONFIG_FILE", "/override/path/to/config.file");

        boolean modified = writer.updateFile(context, tempFile);

        assertTrue(modified);
        assertEquals(Files.readString(EXPECTED_RESULT_FILE), Files.readString(tempFile));
        verify(context, times(1)).log(any(Logger.class), any(String.class));

        modified = writer.updateFile(context, tempFile);
        assertFalse(modified);
        assertEquals(Files.readString(EXPECTED_RESULT_FILE), Files.readString(tempFile));
        verify(context, times(1)).log(any(Logger.class), any(String.class));
    }

}