class DummyClass_77132 {
    @Test
    public void nestedValidation() throws IOException {
        JsonSchema jsonSchema = schemaFactory.getSchema(schemaStr);
        Set<ValidationMessage> validationMessages = jsonSchema.validate(mapper.readTree(person));

        System.err.println("\n" + Arrays.toString(validationMessages.toArray()));

        assertFalse(validationMessages.isEmpty());


    }

}