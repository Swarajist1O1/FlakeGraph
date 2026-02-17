class DummyClass_89327 {
  @Test
  public void testToKafkaSpecForCheckpointStreamShouldReturnTheCorrectStreamSpecByPreservingTheConfig() {
    String topicName = "testStream";
    String streamId = "samza-internal-checkpoint-stream-id";
    int partitionCount = 1;
    Map<String, String> map = new HashMap<>();
    map.put("cleanup.policy", "compact");
    map.put("replication.factor", "3");
    map.put("segment.bytes", "536870912");
    map.put("delete.retention.ms", "86400000");

    Config config = new MapConfig(map);

    StreamSpec spec = new StreamSpec(streamId, topicName, SYSTEM, partitionCount, config);
    KafkaSystemAdmin kafkaSystemAdmin = systemAdmin();
    KafkaStreamSpec kafkaStreamSpec = kafkaSystemAdmin.toKafkaSpec(spec);
    System.out.println(kafkaStreamSpec);
    assertEquals(streamId, kafkaStreamSpec.getId());
    assertEquals(topicName, kafkaStreamSpec.getPhysicalName());
    assertEquals(partitionCount, kafkaStreamSpec.getPartitionCount());
    assertEquals(3, kafkaStreamSpec.getReplicationFactor());
    assertEquals("compact", kafkaStreamSpec.getConfig().get("cleanup.policy"));
    assertEquals("536870912", kafkaStreamSpec.getConfig().get("segment.bytes"));
    assertEquals("86400000", kafkaStreamSpec.getConfig().get("delete.retention.ms"));
  }

}