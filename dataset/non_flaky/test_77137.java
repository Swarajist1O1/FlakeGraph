class DummyClass_77137 {
    @Test(/*expected = java.lang.StackOverflowError.class*/)
    public void testLoadingWithId() throws Exception {
        URL url = new URL("http://localhost:1234/self_ref/selfRef.json");
        JsonNode schemaJson = mapper.readTree(url);
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        @SuppressWarnings("unused")
        JsonSchema schema = factory.getSchema(schemaJson);
    }

}