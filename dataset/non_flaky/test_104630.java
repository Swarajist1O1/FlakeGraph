class DummyClass_104630 {
  @Test
  public void testInvalidTableConfig() {
    TableConfig tableConfig = new TableConfigBuilder(TableType.OFFLINE).setTableName("badTable").build();
    ObjectNode tableConfigJson = (ObjectNode) tableConfig.toJsonNode();
    // Remove a mandatory field
    tableConfigJson.remove(TableConfig.VALIDATION_CONFIG_KEY);
    try {
      sendPostRequest(_controllerRequestURLBuilder.forTableCreate(), tableConfigJson.toString());
      fail();
    } catch (IOException e) {
      // Should get response code 400 (BAD_REQUEST)
      assertTrue(e.getMessage().startsWith("Server returned HTTP response code: 400"));
    }
  }

}