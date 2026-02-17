class DummyClass_77115 {
  @Test
  public void secondOneValid() throws Exception {
    String dataPath = "/data/issue366.json";

    InputStream dataInputStream = getClass().getResourceAsStream(dataPath);
    JsonNode node = getJsonNodeFromStreamContent(dataInputStream);
    List<JsonNode> testNodes = node.findValues("tests");
    JsonNode testNode = testNodes.get(0).get(1);
    JsonNode dataNode = testNode.get("data");
    Set<ValidationMessage> errors = jsonSchema.validate(dataNode);
    assertTrue(errors.isEmpty());
  }

}