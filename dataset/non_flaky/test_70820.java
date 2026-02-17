class DummyClass_70820 {
    @Test
    public void newConverterShouldConfigureWithPluginClassLoader() {
        TestPlugins.assertAvailable();
        props.put(WorkerConfig.KEY_CONVERTER_CLASS_CONFIG, TestPlugins.SAMPLING_CONVERTER);
        ClassLoader classLoader = plugins.delegatingLoader().pluginClassLoader(TestPlugins.SAMPLING_CONVERTER);
        ClassLoader savedLoader = Plugins.compareAndSwapLoaders(classLoader);
        createConfig();
        Plugins.compareAndSwapLoaders(savedLoader);

        Converter plugin = plugins.newConverter(
            config,
            WorkerConfig.KEY_CONVERTER_CLASS_CONFIG,
            ClassLoaderUsage.PLUGINS
        );

        assertInstanceOf(SamplingTestPlugin.class, plugin, "Cannot collect samples");
        Map<String, SamplingTestPlugin> samples = ((SamplingTestPlugin) plugin).flatten();
        assertTrue(samples.containsKey("configure"));
        assertPluginClassLoaderAlwaysActive(samples);
    }

}