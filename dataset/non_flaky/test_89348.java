class DummyClass_89348 {
  @Test
  public void testISDConfigsWithDefaults() {
    KafkaSystemDescriptor sd = new KafkaSystemDescriptor("kafka")
        .withConsumerZkConnect(ImmutableList.of("localhost:123"))
        .withProducerBootstrapServers(ImmutableList.of("localhost:567", "localhost:890"));

    KafkaInputDescriptor<KV<String, Integer>> isd =
        sd.getInputDescriptor("input-stream", KVSerde.of(new StringSerde(), new IntegerSerde()));

    Map<String, String> generatedConfigs = isd.toConfig();
    assertEquals("kafka", generatedConfigs.get("streams.input-stream.samza.system"));
    assertEquals(1, generatedConfigs.size()); // verify that there are no other configs
  }

}