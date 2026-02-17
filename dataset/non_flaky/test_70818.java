class DummyClass_70818 {
    @Test
    public void newPluginShouldInstantiateWithPluginClassLoader() {
        TestPlugins.assertAvailable();
        Converter plugin = plugins.newPlugin(
            TestPlugins.ALIASED_STATIC_FIELD,
            new AbstractConfig(new ConfigDef(), Collections.emptyMap()),
            Converter.class
        );

        assertInstanceOf(SamplingTestPlugin.class, plugin, "Cannot collect samples");
        Map<String, SamplingTestPlugin> samples = ((SamplingTestPlugin) plugin).flatten();
        assertPluginClassLoaderAlwaysActive(samples);
    }

}