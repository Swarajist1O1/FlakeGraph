class DummyClass_77191 {
    @Test
    public void shouldFailV201909() throws Exception {
        String schemaPath = "/schema/issue313-2019-09.json";
        String dataPath = "/data/issue313.json";
        InputStream schemaInputStream = getClass().getResourceAsStream(schemaPath);
        JsonSchema schema = getJsonSchemaFromStreamContentV201909(schemaInputStream);
        InputStream dataInputStream = getClass().getResourceAsStream(dataPath);
        JsonNode node = getJsonNodeFromStreamContent(dataInputStream);
        Set<ValidationMessage> errors = schema.validate(node);
        Assertions.assertEquals(2, errors.size());
    }

}