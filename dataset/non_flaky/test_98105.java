class DummyClass_98105 {
  @Test(expected = IllegalArgumentException.class)
  public void only_valid_stream_type_values_allowed_as_config_property() {
    // given
    final JsonObject withInvalidStreamType = new JsonObject().put("streamType", "unrecognized");

    // expect thrown
    new MongoClientOptionsParser(vertx, withInvalidStreamType).settings();
  }

}