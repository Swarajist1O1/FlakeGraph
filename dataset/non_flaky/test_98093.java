class DummyClass_98093 {
  @Test
  public void testEmptyCaPathProperty() {
    // given
    final JsonObject withSSLwithoutCaPath = new JsonObject().put("ssl", true);

    // when
    final SslSettings sslSettings = new MongoClientOptionsParser(vertx, withSSLwithoutCaPath)
      .settings()
      .getSslSettings();

    // then
    assertNotNull(sslSettings.getContext());
  }

}