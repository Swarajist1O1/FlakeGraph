class DummyClass_98075 {
  @Test
  public void testWriteConcernCaseInsensitive() {
    JsonObject config = new JsonObject();
    config.put("writeConcern", "acknowledged");

    WriteConcern wc = new WriteConcernParser(null, config).writeConcern();
    assertNotNull(wc);
    assertEquals(WriteConcern.ACKNOWLEDGED, wc);
  }

}