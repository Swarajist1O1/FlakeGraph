class DummyClass_70823 {
    @Test
    public void newPluginsShouldConfigureWithPluginClassLoader() {
        TestPlugins.assertAvailable();
        List<Configurable> configurables = plugins.newPlugins(
            Collections.singletonList(TestPlugins.SAMPLING_CONFIGURABLE),
            config,
            Configurable.class
        );
        assertEquals(1, configurables.size());
        Configurable plugin = configurables.get(0);

        assertInstanceOf(SamplingTestPlugin.class, plugin, "Cannot collect samples");
        Map<String, SamplingTestPlugin> samples = ((SamplingTestPlugin) plugin).flatten();
        assertTrue(samples.containsKey("configure")); // Configurable::configure was called
        assertPluginClassLoaderAlwaysActive(samples);
    }

}