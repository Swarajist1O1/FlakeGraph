class DummyClass_60895 {
  @Test
  public void testSerDeNotRequiredKafkaProducerConfig()
  {
    KafkaEmitterConfig kafkaEmitterConfig = new KafkaEmitterConfig("localhost:9092", "metricTest",
        "alertTest", null,
        "clusterNameTest", null
    );
    try {
      @SuppressWarnings("unused")
      KafkaEmitter emitter = new KafkaEmitter(kafkaEmitterConfig, mapper);
    }
    catch (NullPointerException e) {
      Assert.fail();
    }
  }

}