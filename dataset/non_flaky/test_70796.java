class DummyClass_70796 {
    @Test
    public void testThirdPartyClasses() {
        assertFalse(PluginUtils.shouldLoadInIsolation("org.slf4j."));
        assertFalse(PluginUtils.shouldLoadInIsolation("org.slf4j.LoggerFactory"));
    }

}