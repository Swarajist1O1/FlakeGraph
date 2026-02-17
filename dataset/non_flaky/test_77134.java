class DummyClass_77134 {
    @Test
    public void typeValidation() throws IOException, URISyntaxException {
        URI uri = new URI("https://json-schema.org/draft/2019-09/schema");
        JsonSchema jsonSchema = schemaFactory.getSchema(uri);
        Set<ValidationMessage> validationMessages = jsonSchema.validate(mapper.readTree(invalidSchema));

        System.err.println("\n" + Arrays.toString(validationMessages.toArray()));

        assertFalse(validationMessages.isEmpty());
    }

}