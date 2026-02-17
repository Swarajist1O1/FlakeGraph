class DummyClass_60865 {
  @Test
  public void testClusterPriority() throws IOException
  {
    ObjectMapper mapper = new ObjectMapper();
    RedisCacheConfig fromJson = mapper.readValue("{\"expiration\": 1000,"
                                                 + "\"cluster\": {"
                                                 + "\"nodes\": \"127.0.0.1:6379\""
                                                 + "},"
                                                 + "\"host\": \"127.0.0.1\","
                                                 + "\"port\": 6379"
                                                 + "}", RedisCacheConfig.class);

    try (Cache cache = RedisCacheFactory.create(fromJson)) {
      Assert.assertTrue(cache instanceof RedisClusterCache);
    }
  }

}