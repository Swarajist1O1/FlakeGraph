class DummyClass_98080 {
  @Test
  public void testAdvancedWriteConcern_w_int_only() {
    WriteConcern expected = new WriteConcern(123);
    JsonObject config = new JsonObject();
    config.put("w", 123);

    WriteConcern wc = new WriteConcernParser(null, config).writeConcern();
    assertNotNull(wc);
    assertEquals(expected, wc);
  }

}