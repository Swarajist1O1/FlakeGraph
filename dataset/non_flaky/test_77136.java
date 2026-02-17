class DummyClass_77136 {
    @Test
    public void expectObjectNotIntegerV7() throws Exception {
        String schemaPath = "/schema/issue404-v7.json";
        String dataPath = "/data/issue404.json";
        InputStream schemaInputStream = getClass().getResourceAsStream(schemaPath);
        JsonSchema schema = getJsonSchemaFromStreamContentV7(schemaInputStream);
        InputStream dataInputStream = getClass().getResourceAsStream(dataPath);
        JsonNode node = getJsonNodeFromStreamContent(dataInputStream);
        Set<ValidationMessage> errors = schema.validate(node);
        Assertions.assertEquals(0, errors.size());
    }

}