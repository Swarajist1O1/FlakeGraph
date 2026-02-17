class DummyClass_70799 {
    @Test
    public void testClientConfigProvider() {
        assertFalse(PluginUtils.shouldLoadInIsolation(
                "org.apache.kafka.common.config.provider.ConfigProvider")
        );
        assertTrue(PluginUtils.shouldLoadInIsolation(
                "org.apache.kafka.common.config.provider.FileConfigProvider")
        );
        assertTrue(PluginUtils.shouldLoadInIsolation(
                "org.apache.kafka.common.config.provider.FutureConfigProvider")
        );
    }

}