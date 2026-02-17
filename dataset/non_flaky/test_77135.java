class DummyClass_77135 {
    @Test
    public void shouldWorkV7() throws Exception {
        String schemaPath = "/schema/issue426-v7.json";
        String dataPath = "/data/issue426.json";
        InputStream schemaInputStream = getClass().getResourceAsStream(schemaPath);
        JsonSchema schema = getJsonSchemaFromStreamContentV7(schemaInputStream);
        InputStream dataInputStream = getClass().getResourceAsStream(dataPath);
        JsonNode node = getJsonNodeFromStreamContent(dataInputStream);
        Set<ValidationMessage> errors = schema.validate(node);
        Assertions.assertEquals(2, errors.size());
        final JsonNode message = schema.schemaNode.get("message");
        for(ValidationMessage error : errors) {
            //validating custom message
            Assertions.assertEquals(message.get(error.getType()).asText(),  error.getMessage());
        }
    }

}