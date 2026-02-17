class DummyClass_77192 {
    @Test
    public void shouldFailV7() throws Exception {
        String schemaPath = "/schema/issue313-v7.json";
        String dataPath = "/data/issue313.json";
        InputStream schemaInputStream = getClass().getResourceAsStream(schemaPath);
        JsonSchema schema = getJsonSchemaFromStreamContentV7(schemaInputStream);
        InputStream dataInputStream = getClass().getResourceAsStream(dataPath);
        JsonNode node = getJsonNodeFromStreamContent(dataInputStream);
        Set<ValidationMessage> errors = schema.validate(node);
        Assertions.assertEquals(2, errors.size());
    }

}