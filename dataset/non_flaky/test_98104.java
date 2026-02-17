class DummyClass_98104 {
  @Test
  public void should_parse_stream_type_from_config_property(String streamTypeString, Class<StreamFactoryFactory> streamType) {
    // given
    final JsonObject cfgWithStreamTypeProvided = new JsonObject().put("streamType", streamTypeString);

    // when
    final MongoClientSettings parsedSettings = new MongoClientOptionsParser(vertx, cfgWithStreamTypeProvided).settings();

    // then
    assertThat(parsedSettings.getStreamFactoryFactory(), instanceOf(streamType));
  }

}