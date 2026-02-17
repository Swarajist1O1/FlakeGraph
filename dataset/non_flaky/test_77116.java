class DummyClass_77116 {
  @Test
  public void bothValid() throws Exception {
    String dataPath = "/data/issue366.json";

    assertThrows(JsonSchemaException.class, () -> {
        InputStream dataInputStream = getClass().getResourceAsStream(dataPath);
        JsonNode node = getJsonNodeFromStreamContent(dataInputStream);
        List<JsonNode> testNodes = node.findValues("tests");
        JsonNode testNode = testNodes.get(0).get(2);
        JsonNode dataNode = testNode.get("data");
        jsonSchema.validate(dataNode);
    });
  }

}