class DummyClass_77180 {
    @Test
    public void testFailFast_NoErrors() throws IOException {
        try {
            final Set<ValidationMessage> messages = validateFailingFastSchemaFor("product.schema.json", "product-no-errors-data.json");
            assertTrue(messages.isEmpty());
        } catch (JsonSchemaException e) {
            fail("Must not get an errors");
        }
    }

}