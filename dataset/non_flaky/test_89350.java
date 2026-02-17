class DummyClass_89350 {
  @Test
  public void testSDConfigsWithoutOverrides() {
    KafkaSystemDescriptor sd = new KafkaSystemDescriptor("kafka");

    Map<String, String> generatedConfigs = sd.toConfig();
    assertEquals("org.apache.samza.system.kafka.KafkaSystemFactory", generatedConfigs.get("systems.kafka.samza.factory"));
    assertEquals(1, generatedConfigs.size()); // verify that there are no other configs
  }

}