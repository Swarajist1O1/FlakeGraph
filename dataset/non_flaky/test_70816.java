class DummyClass_70816 {
    @Test
    public void shouldShareStaticValuesBetweenSamePlugin() {
        // Plugins are not isolated from other instances of their own class.
        TestPlugins.assertAvailable();
        Converter firstPlugin = plugins.newPlugin(
            TestPlugins.ALIASED_STATIC_FIELD,
            new AbstractConfig(new ConfigDef(), Collections.emptyMap()),
            Converter.class
        );

        assertInstanceOf(SamplingTestPlugin.class, firstPlugin, "Cannot collect samples");

        Converter secondPlugin = plugins.newPlugin(
            TestPlugins.ALIASED_STATIC_FIELD,
            new AbstractConfig(new ConfigDef(), Collections.emptyMap()),
            Converter.class
        );

        assertInstanceOf(SamplingTestPlugin.class, secondPlugin, "Cannot collect samples");
        assertSame(
            ((SamplingTestPlugin) firstPlugin).otherSamples(),
            ((SamplingTestPlugin) secondPlugin).otherSamples()
        );
    }

}