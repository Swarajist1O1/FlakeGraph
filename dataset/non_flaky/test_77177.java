class DummyClass_77177 {
    @Test
    public void testFailFast_AllErrors() throws IOException {
        try {
            validateFailingFastSchemaFor("product.schema.json", "product-all-errors-data.json");
            fail("Exception must be thrown");
        } catch (JsonSchemaException e) {
            final Set<ValidationMessage> messages = e.getValidationMessages();
            assertEquals(1, messages.size());
        }
    }

}