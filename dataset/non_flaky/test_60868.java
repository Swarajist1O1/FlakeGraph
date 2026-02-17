class DummyClass_60868 {
  @Test
  public void testInvalidClusterNodePort0() throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();
    RedisCacheConfig fromJson = mapper.readValue(
        "{\"expiration\": 1000,"
        + "\"cluster\": {"
        + "\"nodes\": \"127.0.0.1:0\"" //<===Invalid Port
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