class DummyClass_70819 {
    @Test(expected = ConfigException.class)
    public void shouldFailToFindConverterInCurrentClassloader() {
        TestPlugins.assertAvailable();
        props.put(WorkerConfig.KEY_CONVERTER_CLASS_CONFIG, TestPlugins.SAMPLING_CONVERTER);
        createConfig();
    }

}