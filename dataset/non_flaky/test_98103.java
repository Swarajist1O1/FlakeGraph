class DummyClass_98103 {
  @Test
  public void should_not_include_any_stream_type_by_default_for_backwards_compatibility() {
    // given
    final JsonObject noStreamTypeProvided = new JsonObject().put(
      "connection_string", "mongodb://localhost:27017/mydb?replicaSet=myRs"
    );

    // when
    final MongoClientSettings parsedSettings = new MongoClientOptionsParser(vertx, noStreamTypeProvided).settings();

    // then
    assertNull(parsedSettings.getStreamFactoryFactory());
  }

}