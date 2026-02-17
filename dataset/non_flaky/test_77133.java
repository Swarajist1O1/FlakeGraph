class DummyClass_77133 {
    @Test
    public void nestedTypeValidation() throws IOException, URISyntaxException {
        URI uri = new URI("https://json-schema.org/draft/2019-09/schema");
        JsonSchema jsonSchema = schemaFactory.getSchema(uri);
        Set<ValidationMessage> validationMessages = jsonSchema.validate(mapper.readTree(invalidNestedSchema));

        System.err.println("\n" + Arrays.toString(validationMessages.toArray()));

        assertFalse(validationMessages.isEmpty());
    }

}