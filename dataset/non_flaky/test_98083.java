class DummyClass_98083 {
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidWriteConcern_w_boolean() {
    JsonObject config = new JsonObject();
    config.put("w", true);

    new WriteConcernParser(null, config).writeConcern();
  }

}