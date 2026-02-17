class DummyClass_77185 {
    @Test
    public void shouldWorkT2() throws Exception {
        String schemaPath = "/schema/issue456-v7.json";
        String dataPath = "/data/issue456-T2.json";
        String dataT3Path = "/data/issue456-T3.json";
        InputStream schemaInputStream = getClass().getResourceAsStream(schemaPath);
        JsonSchema schema = getJsonSchemaFromStreamContentV7(schemaInputStream);
        InputStream dataInputStream = getClass().getResourceAsStream(dataPath);
        JsonNode node = getJsonNodeFromStreamContent(dataInputStream);
        Set<ValidationMessage> errors = schema.validate(node);
        Assertions.assertEquals(0, errors.size());
    }

}