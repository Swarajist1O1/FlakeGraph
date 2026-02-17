class DummyClass_77179 {
    @Test
    public void testFailFast_TwoErrors() throws IOException {
        try {
            validateFailingFastSchemaFor("product.schema.json", "product-two-errors-data.json");
            fail("Exception must be thrown");
        } catch (JsonSchemaException e) {
            final Set<ValidationMessage> messages = e.getValidationMessages();
            assertEquals(1, messages.size());
        }
    }

}