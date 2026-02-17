class DummyClass_98088 {
  @Test
  public void one_should_be_able_to_enable_ssl_support_via_connection_string() {
    // given
    final JsonObject withSSLEnabled = new JsonObject().put(
      "connection_string", "mongodb://localhost:27017/mydb?replicaSet=myRs&ssl=true"
    );

    // when
    final SslSettings sslSettings = new MongoClientOptionsParser(vertx, withSSLEnabled).settings().getSslSettings();

    // then
    assertTrue(sslSettings.isEnabled());
  }

}