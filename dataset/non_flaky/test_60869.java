class DummyClass_60869 {
  @Test
  public void testInvalidClusterNodePort65536() throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();
    RedisCacheConfig fromJson = mapper.readValue(
        "{\"expiration\": 1000,"
        + "\"cluster\": {"
        + "\"nodes\": \"127.0.0.1:65536\"" //<===Invalid Port
        + "}"
        + "}",
        RedisCacheConfig.class
    );

    expectedException.expect(new ExceptionMatcher(
        IAE.class,
        new ContainsMatcher("Invalid port")
    ));
    RedisCacheFactory.create(fromJson);
  }

}