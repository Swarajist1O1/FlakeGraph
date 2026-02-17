class DummyClass_70810 {
    @Test
    public void shouldInstantiateAndConfigureConverters() {
        instantiateAndConfigureConverter(WorkerConfig.KEY_CONVERTER_CLASS_CONFIG, ClassLoaderUsage.CURRENT_CLASSLOADER);
        // Validate extra configs got passed through to overridden converters
        assertEquals("true", converter.configs.get(JsonConverterConfig.SCHEMAS_ENABLE_CONFIG));
        assertEquals("foo1", converter.configs.get("extra.config"));

        instantiateAndConfigureConverter(WorkerConfig.VALUE_CONVERTER_CLASS_CONFIG, ClassLoaderUsage.PLUGINS);
        // Validate extra configs got passed through to overridden converters
        assertEquals("true", converter.configs.get(JsonConverterConfig.SCHEMAS_ENABLE_CONFIG));
        assertEquals("foo2", converter.configs.get("extra.config"));
    }

}