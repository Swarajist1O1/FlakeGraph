class DummyClass_70822 {
    @Test
    public void newHeaderConverterShouldConfigureWithPluginClassLoader() {
        TestPlugins.assertAvailable();
        props.put(WorkerConfig.HEADER_CONVERTER_CLASS_CONFIG, TestPlugins.SAMPLING_HEADER_CONVERTER);
        ClassLoader classLoader = plugins.delegatingLoader().pluginClassLoader(TestPlugins.SAMPLING_HEADER_CONVERTER);
        ClassLoader savedLoader = Plugins.compareAndSwapLoaders(classLoader);
        createConfig();
        Plugins.compareAndSwapLoaders(savedLoader);

        HeaderConverter plugin = plugins.newHeaderConverter(
            config,
            WorkerConfig.HEADER_CONVERTER_CLASS_CONFIG,
            ClassLoaderUsage.PLUGINS
        );

        assertInstanceOf(SamplingTestPlugin.class, plugin, "Cannot collect samples");
        Map<String, SamplingTestPlugin> samples = ((SamplingTestPlugin) plugin).flatten();
        assertTrue(samples.containsKey("configure")); // HeaderConverter::configure was called
        assertPluginClassLoaderAlwaysActive(samples);
    }

}