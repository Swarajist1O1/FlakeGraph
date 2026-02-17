class DummyClass_98094 {
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCaPathProperty() {
    // given
    final JsonObject withSSLAndCaPath = new JsonObject()
      .put("ssl", true)
      .put("caPath", "notExisting.pem");

    // then
    new MongoClientOptionsParser(vertx, withSSLAndCaPath);
  }

}