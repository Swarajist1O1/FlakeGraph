class DummyClass_98087 {
  @Test
  public void ssl_should_be_disabled_by_default() {
    // given
    final JsonObject configWithoutSSLInfo = new JsonObject().put(
      "connection_string", "mongodb://localhost:27017/mydb?replicaSet=myRs"
    );

    // when
    final MongoClientSettings parsedSettings = new MongoClientOptionsParser(vertx, configWithoutSSLInfo).settings();

    // then
    assertFalse(parsedSettings.getSslSettings().isEnabled());
    assertFalse(parsedSettings.getSslSettings().isInvalidHostNameAllowed());
  }

}