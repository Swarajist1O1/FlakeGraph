class DummyClass_77109 {
    @ParameterizedTest
    public void dataIsValid(boolean failFast) throws Exception {
        String schemaPath = "/schema/issue386-v7.json";
        String dataPath = "/data/issue386.json";
        JsonSchema schema = getJsonSchemaFromPathV7(schemaPath, failFast);
        JsonNode node = getJsonNodeFromPath(dataPath).get("valid");
        node.forEach(testNode -> {
            Set<ValidationMessage> errors = schema.validate(testNode.get("data"));
            Assertions.assertEquals(0, errors.size(), "Expected no errors for " + testNode.get("data"));
        });
    }

}