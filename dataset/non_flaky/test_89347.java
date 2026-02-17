class DummyClass_89347 {
  @Test
  public void testISDConfigsWithOverrides() {
    KafkaSystemDescriptor sd = new KafkaSystemDescriptor("kafka");

    KafkaInputDescriptor<KV<String, Integer>> isd =
        sd.getInputDescriptor("input-stream", KVSerde.of(new StringSerde(), new IntegerSerde()))
            .withConsumerAutoOffsetReset("largest")
            .withConsumerFetchMessageMaxBytes(1024 * 1024);

    Map<String, String> generatedConfigs = isd.toConfig();
    assertEquals("kafka", generatedConfigs.get("streams.input-stream.samza.system"));
    assertEquals("largest", generatedConfigs.get("systems.kafka.streams.input-stream.consumer.auto.offset.reset"));
    assertEquals("1048576", generatedConfigs.get("systems.kafka.streams.input-stream.consumer.fetch.message.max.bytes"));
  }

}