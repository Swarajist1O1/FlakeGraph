class DummyClass_98090 {
  @Test
  public void one_should_be_able_to_allow_invalid_host_names_via_connection_string() {
    // given
    final JsonObject withSSLAndInvalidHostnameEnabled = new JsonObject().put(
      "connection_string", "mongodb://localhost:27017/mydb?replicaSet=myRs&ssl=true&sslInvalidHostNameAllowed=true"
    );

    // when
    final SslSettings sslSettings = new MongoClientOptionsParser(vertx, withSSLAndInvalidHostnameEnabled)
      .settings()
      .getSslSettings();

    // then
    assertTrue(sslSettings.isInvalidHostNameAllowed());
  }

}