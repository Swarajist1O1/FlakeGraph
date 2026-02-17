class DummyClass_77131 {
    @Test
    public void propertyNameEnumShouldFailV7() throws Exception {
        String schemaPath = "/schema/issue342-v7.json";
        String dataPath = "/data/issue342.json";
        InputStream schemaInputStream = getClass().getResourceAsStream(schemaPath);
        JsonSchema schema = getJsonSchemaFromStreamContentV7(schemaInputStream);
        InputStream dataInputStream = getClass().getResourceAsStream(dataPath);
        JsonNode node = getJsonNodeFromStreamContent(dataInputStream);
        Set<ValidationMessage> errors = schema.validate(node);
        Assertions.assertEquals(1, errors.size());
        final ValidationMessage error = errors.iterator().next();
        Assertions.assertEquals("$.z", error.getPath());
        Assertions.assertEquals("Property name $.z is not valid for validation: does not have a value in the enumeration [a, b, c]", error.getMessage());
    }

}