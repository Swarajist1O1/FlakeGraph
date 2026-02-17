class DummyClass_104697 {
  @Test(expectedExceptions = IOException.class)
  public void testAddHLCTableShouldFail()
      throws IOException {
    TableConfig tableConfig = new TableConfigBuilder(TableType.REALTIME).setTableName("testTable")
        .setStreamConfigs(Collections.singletonMap("stream.kafka.consumer.type", "HIGHLEVEL")).build();
    sendPostRequest(_controllerRequestURLBuilder.forTableCreate(), tableConfig.toJsonString());
  }

}