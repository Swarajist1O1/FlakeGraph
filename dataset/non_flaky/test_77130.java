class DummyClass_77130 {
    @Test
    public void shouldFailWhenRequiredPropertiesDoNotExistInReferencedSubSchema() throws Exception {
        String schemaPath = "/draft2019-09/issue255.json";
        String dataPath = "/data/issue255.json";
        InputStream schemaInputStream = getClass().getResourceAsStream(schemaPath);
        JsonSchema schema = getJsonSchemaFromStreamContent(schemaInputStream);
        InputStream dataInputStream = getClass().getResourceAsStream(dataPath);
        JsonNode node = getJsonNodeFromStreamContent(dataInputStream);
        Set<ValidationMessage> errors = schema.validate(node);
        Assertions.assertEquals(2, errors.size());
    }

}