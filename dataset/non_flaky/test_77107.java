class DummyClass_77107 {
    @Test
    public void testPreloadingHappening() {
        final JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        final JsonSchema schema = factory.getSchema(INVALID_$REF_SCHEMA);
        Assertions.assertThrows(JsonSchemaException.class,
                            new Executable() {
                                @Override
                                public void execute() {
                                    schema.initializeValidators();
                                }

}