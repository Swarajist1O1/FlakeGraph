class DummyClass_70811 {
    @Test
    public void shouldInstantiateAndConfigureInternalConverters() {
        instantiateAndConfigureInternalConverter(WorkerConfig.INTERNAL_KEY_CONVERTER_CLASS_CONFIG, ClassLoaderUsage.CURRENT_CLASSLOADER);
        // Validate schemas.enable is defaulted to false for internal converter
        assertEquals(false, internalConverter.configs.get(JsonConverterConfig.SCHEMAS_ENABLE_CONFIG));
        // Validate internal converter properties can still be set
        assertEquals("bar1", internalConverter.configs.get("extra.config"));

        instantiateAndConfigureInternalConverter(WorkerConfig.INTERNAL_VALUE_CONVERTER_CLASS_CONFIG, ClassLoaderUsage.PLUGINS);
        // Validate schemas.enable is defaulted to false for internal converter
        assertEquals(false, internalConverter.configs.get(JsonConverterConfig.SCHEMAS_ENABLE_CONFIG));
        // Validate internal converter properties can still be set
        assertEquals("bar2", internalConverter.configs.get("extra.config"));
    }

}