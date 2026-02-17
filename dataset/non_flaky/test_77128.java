class DummyClass_77128 {
    @Test
    public void nestedOneOfsShouldStillMatchV7() throws Exception {
        String schemaPath = "/schema/issue383-v7.json";
        String dataPath = "/data/issue383.json";
        InputStream schemaInputStream = getClass().getResourceAsStream(schemaPath);
        JsonSchema schema = getJsonSchemaFromStreamContentV7(schemaInputStream);
        InputStream dataInputStream = getClass().getResourceAsStream(dataPath);
        JsonNode node = getJsonNodeFromStreamContent(dataInputStream);
        Set<ValidationMessage> errors = schema.validate(node);
        Assertions.assertEquals(0, errors.size());
    }

}