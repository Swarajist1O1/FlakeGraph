class DummyClass_89356 {
  @Test
  public void testForwardsCompatibility() {
    // Set the key to another value, this is for the future if we want to support multiple checkpoint keys
    // we do not want to throw in the Serdes layer, but must be validated in the CheckpointManager
    KafkaCheckpointLogKey key = new KafkaCheckpointLogKey("checkpoint-v2",
        new TaskName("Partition 0"), GroupByPartitionFactory.class.getCanonicalName());
    KafkaCheckpointLogKeySerde checkpointSerde = new KafkaCheckpointLogKeySerde();

    // test that deserialize(serialize(k)) == k
    Assert.assertEquals(key, checkpointSerde.fromBytes(checkpointSerde.toBytes(key)));
  }

}