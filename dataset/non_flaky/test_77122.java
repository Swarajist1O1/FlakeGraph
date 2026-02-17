class DummyClass_77122 {
    @Test
    public void shouldWalkWithValidation() throws URISyntaxException, IOException {
        JsonSchema schema = getJsonSchemaFromStreamContentV7(new URI("http://json-schema" +
                ".org/draft-07/schema#"));
        JsonNode data = mapper.readTree(Issue461Test.class.getResource("/data/issue461-v7.json"));
        ValidationResult result = schema.walk(data, true);
        Assertions.assertTrue(result.getValidationMessages().isEmpty());
    }

}