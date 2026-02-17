class DummyClass_70815 {
    @Test(expected = ConnectException.class)
    public void shouldThrowIfPluginThrows() {
        TestPlugins.assertAvailable();

        plugins.newPlugin(
            TestPlugins.ALWAYS_THROW_EXCEPTION,
            new AbstractConfig(new ConfigDef(), Collections.emptyMap()),
            Converter.class
        );
    }

}