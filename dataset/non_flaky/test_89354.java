class DummyClass_89354 {
  @Test
  public void testSerde() {
    KafkaCheckpointLogKey key = new KafkaCheckpointLogKey(KafkaCheckpointLogKey.CHECKPOINT_V1_KEY_TYPE,
        new TaskName("Partition 0"), GroupByPartitionFactory.class.getCanonicalName());
    KafkaCheckpointLogKeySerde checkpointSerde = new KafkaCheckpointLogKeySerde();

    // test that deserialize(serialize(k)) == k
    Assert.assertEquals(key, checkpointSerde.fromBytes(checkpointSerde.toBytes(key)));
  }

}