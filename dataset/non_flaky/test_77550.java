class DummyClass_77550 {
    @Test
    public void testBuildConfigurationMetadata(CheckedConsumer<String> classFilter) throws Exception {
        try (ByteArrayOutputStream byteStream = captureStderr();
                CustomClassLoader loader = new CustomClassLoader(classFilter)) {
            // create class objects from custom loader
            Class<ConfigurationMetadata> cmType = loader.reloadClass(ConfigurationMetadata.class);
            Class<ObjectMapper> omType = loader.reloadClass(ObjectMapper.class);
            Class<Configuration> confType = loader.reloadClass(Configuration.class);
            // construct ConfigurationMetadata object using class object associated with custom loader so that we can
            // simulate Logback not being in the classpath
            cmType.getConstructor(omType, Class.class).newInstance(omType.newInstance(), confType);

            // make sure nothing is emitted to stderr; previously the absence of Logback in the classpath would cause
            // "class io.dropwizard.configuration.ConfigurationMetadata$1: Type ch.qos.logback.access.spi.IAccessEvent
            // not present" to be emitted to stderr
            String err = byteStream.toString();
            assertThat(err).isEmpty();
        }
    }

}