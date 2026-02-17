class DummyClass_77108 {
    @Test
    public void testPreloadingHappeningForCircularDependency() {
        final JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        final JsonSchema schema = factory.getSchema(CIRCULAR_$REF_SCHEMA);
        schema.initializeValidators();
    }

}