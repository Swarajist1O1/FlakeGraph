class DummyClass_98076 {
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidWriteConcern() {
    JsonObject config = new JsonObject();
    config.put("writeConcern", "foo");

    new WriteConcernParser(null, config).writeConcern();
  }

}