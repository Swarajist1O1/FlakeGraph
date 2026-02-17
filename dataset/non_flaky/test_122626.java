class DummyClass_122626 {
    @Test
    public void generates_default_env_content() throws IOException {
        DefaultEnvWriter writer = new DefaultEnvWriter();
        writer.addOverride("VESPA_HOSTNAME", "my-new-hostname");
        writer.addFallback("VESPA_CONFIGSERVER", "new-fallback-configserver");
        writer.addOverride("VESPA_TLS_CONFIG_FILE", "/override/path/to/config.file");
        writer.addUnset("VESPA_LEGACY_OPTION");
        String generatedContent = writer.generateContent();
        assertEquals(Files.readString(EXPECTED_RESULT_FILE), generatedContent);
    }

}