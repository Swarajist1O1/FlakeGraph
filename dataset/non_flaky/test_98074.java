class DummyClass_98074 {
  @Test
  public void testWriteConcern() {
    JsonObject config = new JsonObject();
    config.put("writeConcern", "ACKNOWLEDGED");

    WriteConcern wc = new WriteConcernParser(null, config).writeConcern();
    assertNotNull(wc);
    assertEquals(WriteConcern.ACKNOWLEDGED, wc);
  }

}