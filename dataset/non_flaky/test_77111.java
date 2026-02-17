class DummyClass_77111 {
    @Test
    public void dataIsInvalidFailSlow() throws Exception {
        String schemaPath = "/schema/issue386-v7.json";
        String dataPath = "/data/issue386.json";
        JsonSchema schema = getJsonSchemaFromPathV7(schemaPath, false);
        JsonNode node = getJsonNodeFromPath(dataPath).get("invalid");
        node.forEach(testNode -> {
            Set<ValidationMessage> errors = schema.validate(testNode.get("data"));
            List<String> errorMessages = errors.stream().map(x -> x.getMessage()).collect(Collectors.toList());
            testNode.get("expectedErrors").forEach(expectedError -> {
                Assertions.assertTrue(errorMessages.contains(expectedError.asText()));
            });
        });
    }

}