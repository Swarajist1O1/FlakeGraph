class DummyClass_98092 {
  @Test
  public void testTrustAllProperty() {
    // given
    final JsonObject withSSLAndTrustAllEnabled = new JsonObject()
      .put("ssl", true)
      .put("trustAll", true);

    // when
    final SslSettings sslSettings = new MongoClientOptionsParser(vertx, withSSLAndTrustAllEnabled)
      .settings()
      .getSslSettings();

    // then
    assertNotNull(sslSettings.getContext());
  }

}