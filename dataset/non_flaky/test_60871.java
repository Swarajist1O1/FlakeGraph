class DummyClass_60871 {
  @Test
  public void testConfig() throws JsonProcessingException
  {
    ObjectMapper mapper = new ObjectMapper();
    RedisCacheConfig fromJson = mapper.readValue("{\"expiration\": 1000}", RedisCacheConfig.class);
    Assert.assertEquals(1, fromJson.getExpiration().getSeconds());

    fromJson = mapper.readValue("{\"expiration\": \"PT1H\"}", RedisCacheConfig.class);
    Assert.assertEquals(3600, fromJson.getExpiration().getSeconds());
  }

}