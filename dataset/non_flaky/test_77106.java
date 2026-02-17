class DummyClass_77106 {
    @Test
    public void testPreloadingNotHappening() {
        final JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        final JsonSchema schema = factory.getSchema(INVALID_$REF_SCHEMA);
        // not breaking - pass
        Assertions.assertNotNull(schema);
    }

}