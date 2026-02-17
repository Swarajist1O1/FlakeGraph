class DummyClass_70817 {
    @Test
    public void newPluginShouldServiceLoadWithPluginClassLoader() {
        TestPlugins.assertAvailable();
        Converter plugin = plugins.newPlugin(
            TestPlugins.SERVICE_LOADER,
            new AbstractConfig(new ConfigDef(), Collections.emptyMap()),
            Converter.class
        );

        assertInstanceOf(SamplingTestPlugin.class, plugin, "Cannot collect samples");
        Map<String, SamplingTestPlugin> samples = ((SamplingTestPlugin) plugin).flatten();
        // Assert that the service loaded subclass is found in both environments
        assertTrue(samples.containsKey("ServiceLoadedSubclass.static"));
        assertTrue(samples.containsKey("ServiceLoadedSubclass.dynamic"));
        assertPluginClassLoaderAlwaysActive(samples);
    }

}