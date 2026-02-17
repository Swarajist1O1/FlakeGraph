class DummyClass_60870 {
  @Test
  public void testNoClusterAndHost() throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();
    RedisCacheConfig fromJson = mapper.readValue(
        "{\"expiration\": 1000"
        + "}",
        RedisCacheConfig.class
    );

    expectedException.expect(new ExceptionMatcher(
        IAE.class,
        new ContainsMatcher("no redis server")
    ));
    RedisCacheFactory.create(fromJson);
  }

}