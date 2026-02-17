class DummyClass_98089 {
  @Test
  public void one_should_be_able_to_enable_ssl_support_via_config_property() {
    // given
    final JsonObject withSSLEnabled = new JsonObject().put("ssl", true);

    // when
    final SslSettings sslSettings = new MongoClientOptionsParser(vertx, withSSLEnabled).settings().getSslSettings();

    // then
    assertTrue(sslSettings.isEnabled());
  }

}