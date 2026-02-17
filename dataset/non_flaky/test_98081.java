class DummyClass_98081 {
  @Test
  public void testAdvancedWriteConcern_w_string_only() {
    WriteConcern expected = new WriteConcern("foo");
    JsonObject config = new JsonObject();
    config.put("w", "foo");

    WriteConcern wc = new WriteConcernParser(null, config).writeConcern();
    assertNotNull(wc);
    assertEquals(expected, wc);
  }

}