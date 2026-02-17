class DummyClass_70821 {
    @Test
    public void newConfigProviderShouldConfigureWithPluginClassLoader() {
        TestPlugins.assertAvailable();
        String providerPrefix = "some.provider";
        props.put(providerPrefix + ".class", TestPlugins.SAMPLING_CONFIG_PROVIDER);

        PluginClassLoader classLoader = plugins.delegatingLoader().pluginClassLoader(TestPlugins.SAMPLING_CONFIG_PROVIDER);
        assertNotNull(classLoader);
        ClassLoader savedLoader = Plugins.compareAndSwapLoaders(classLoader);
        createConfig();
        Plugins.compareAndSwapLoaders(savedLoader);

        ConfigProvider plugin = plugins.newConfigProvider(
            config,
            providerPrefix,
            ClassLoaderUsage.PLUGINS
        );

        assertInstanceOf(SamplingTestPlugin.class, plugin, "Cannot collect samples");
        Map<String, SamplingTestPlugin> samples = ((SamplingTestPlugin) plugin).flatten();
        assertTrue(samples.containsKey("configure"));
        assertPluginClassLoaderAlwaysActive(samples);
    }

}