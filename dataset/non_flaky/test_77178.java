class DummyClass_77178 {
    @Test
    public void testFailFast_OneErrors() throws IOException {
        try {
            validateFailingFastSchemaFor("product.schema.json", "product-one-error-data.json");
            fail("Exception must be thrown");
        } catch (JsonSchemaException e) {
            final Set<ValidationMessage> messages = e.getValidationMessages();
            assertEquals(1, messages.size());
        }
    }

}