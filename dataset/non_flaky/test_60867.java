class DummyClass_60867 {
  @Test
  public void testClusterLackOfPort() throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();
    RedisCacheConfig fromJson = mapper.readValue(
        "{\"expiration\":1000,"
        + "\"cluster\": {"
        + "\"nodes\": \"127.0.0.1:\""
        + "}"
        + "}",
        RedisCacheConfig.class
    );

    expectedException.expect(new ExceptionMatcher(
        IAE.class,
        new StartWithMatcher("Invalid port")
    ));
    RedisCacheFactory.create(fromJson);
  }

}