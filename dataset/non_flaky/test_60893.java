class DummyClass_60893 {
  @Test
  public void testSerDeserKafkaEmitterConfig() throws IOException
  {
    KafkaEmitterConfig kafkaEmitterConfig = new KafkaEmitterConfig("hostname", "metricTest",
        "alertTest", "requestTest",
        "clusterNameTest", ImmutableMap.<String, String>builder()
        .put("testKey", "testValue").build()
    );
    String kafkaEmitterConfigString = mapper.writeValueAsString(kafkaEmitterConfig);
    KafkaEmitterConfig kafkaEmitterConfigExpected = mapper.readerFor(KafkaEmitterConfig.class)
        .readValue(kafkaEmitterConfigString);
    Assert.assertEquals(kafkaEmitterConfigExpected, kafkaEmitterConfig);
  }

}