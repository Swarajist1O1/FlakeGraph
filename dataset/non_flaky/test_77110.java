class DummyClass_77110 {
    @Test
    public void dataIsInvalidFailFast() throws Exception {
        String schemaPath = "/schema/issue386-v7.json";
        String dataPath = "/data/issue386.json";
        JsonSchema schema = getJsonSchemaFromPathV7(schemaPath, true);
        JsonNode node = getJsonNodeFromPath(dataPath).get("invalid");
        node.forEach(testNode -> {
            try {
                schema.validate(testNode.get("data"));
                Assertions.fail();
            } catch (JsonSchemaException e) {
                Assertions.assertEquals(testNode.get("expectedErrors").get(0).asText(), e.getMessage());
            }
        });
    }

}