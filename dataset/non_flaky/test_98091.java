class DummyClass_98091 {
  @Test
  public void one_should_be_able_to_allow_invalid_host_names_via_config_property() {
    // given
    final JsonObject withSSLAndInvalidHostnameEnabled = new JsonObject()
      .put("ssl", true)
      .put("sslInvalidHostNameAllowed", true);

    // when
    final SslSettings sslSettings = new MongoClientOptionsParser(vertx, withSSLAndInvalidHostnameEnabled)
      .settings()
      .getSslSettings();

    // then
    assertTrue(sslSettings.isInvalidHostNameAllowed());
  }

}