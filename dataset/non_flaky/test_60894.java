class DummyClass_60894 {
  @Test
  public void testSerDeserKafkaEmitterConfigNullRequestTopic() throws IOException
  {
    KafkaEmitterConfig kafkaEmitterConfig = new KafkaEmitterConfig("hostname", "metricTest",
        "alertTest", null,
        "clusterNameTest", ImmutableMap.<String, String>builder()
        .put("testKey", "testValue").build()
    );
    String kafkaEmitterConfigString = mapper.writeValueAsString(kafkaEmitterConfig);
    KafkaEmitterConfig kafkaEmitterConfigExpected = mapper.readerFor(KafkaEmitterConfig.class)
        .readValue(kafkaEmitterConfigString);
    Assert.assertEquals(kafkaEmitterConfigExpected, kafkaEmitterConfig);
  }

}