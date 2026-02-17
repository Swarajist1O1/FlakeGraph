class DummyClass_60866 {
  @Test
  public void testClusterInvalidNode() throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();
    RedisCacheConfig fromJson = mapper.readValue(
        "{\"expiration\": 1000,"
        + "\"cluster\": {"
        + "\"nodes\": \"127.0.0.1\"" //<===Invalid Node
        + "}"
        + "}",
        RedisCacheConfig.class
    );

    expectedException.expect(new ExceptionMatcher(
        IAE.class,
        new StartWithMatcher("Invalid redis cluster")
    ));
    RedisCacheFactory.create(fromJson);
  }

}