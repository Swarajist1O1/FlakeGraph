class DummyClass_89344 {
  @Test
  public void testUnsupportedConfigStrippedFromProperties() {
    StreamSpec original = new StreamSpec("dummyId", "dummyPhysicalName", "dummySystemName",
        ImmutableMap.of("segment.bytes", "4", "replication.factor", "7"));

    // First verify the original
    assertEquals("7", original.get("replication.factor"));
    assertEquals("4", original.get("segment.bytes"));

    Map<String, String> config = original.getConfig();
    assertEquals("7", config.get("replication.factor"));
    assertEquals("4", config.get("segment.bytes"));


    // Now verify the Kafka spec
    KafkaStreamSpec spec = KafkaStreamSpec.fromSpec(original);
    assertNull(spec.get("replication.factor"));
    assertEquals("4", spec.get("segment.bytes"));

    Properties kafkaProperties = spec.getProperties();
    Map<String, String> kafkaConfig = spec.getConfig();
    assertNull(kafkaProperties.get("replication.factor"));
    assertEquals("4", kafkaProperties.get("segment.bytes"));

    assertNull(kafkaConfig.get("replication.factor"));
    assertEquals("4", kafkaConfig.get("segment.bytes"));
  }

}