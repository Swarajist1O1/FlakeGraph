class DummyClass_70812 {
    @Test
    public void shouldInstantiateAndConfigureExplicitlySetHeaderConverterWithCurrentClassLoader() {
        assertNotNull(props.get(WorkerConfig.HEADER_CONVERTER_CLASS_CONFIG));
        HeaderConverter headerConverter = plugins.newHeaderConverter(config,
                                                                     WorkerConfig.HEADER_CONVERTER_CLASS_CONFIG,
                                                                     ClassLoaderUsage.CURRENT_CLASSLOADER);
        assertNotNull(headerConverter);
        assertTrue(headerConverter instanceof TestHeaderConverter);
        this.headerConverter = (TestHeaderConverter) headerConverter;

        // Validate extra configs got passed through to overridden converters
        assertConverterType(ConverterType.HEADER, this.headerConverter.configs);
        assertEquals("baz", this.headerConverter.configs.get("extra.config"));

        headerConverter = plugins.newHeaderConverter(config,
                                                     WorkerConfig.HEADER_CONVERTER_CLASS_CONFIG,
                                                     ClassLoaderUsage.PLUGINS);
        assertNotNull(headerConverter);
        assertTrue(headerConverter instanceof TestHeaderConverter);
        this.headerConverter = (TestHeaderConverter) headerConverter;

        // Validate extra configs got passed through to overridden converters
        assertConverterType(ConverterType.HEADER, this.headerConverter.configs);
        assertEquals("baz", this.headerConverter.configs.get("extra.config"));
    }

}