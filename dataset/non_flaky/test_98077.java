class DummyClass_98077 {
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTypeWriteConcern() {
    JsonObject config = new JsonObject();
    config.put("writeConcern", 123);

    new WriteConcernParser(null, config);
  }

}