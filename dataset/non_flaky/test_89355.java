class DummyClass_89355 {
  @Test
  public void testCheckpointTypeV2() {
    KafkaCheckpointLogKey keyV2 = new KafkaCheckpointLogKey(KafkaCheckpointLogKey.CHECKPOINT_V2_KEY_TYPE, new TaskName("Partition 0"),
        GroupByPartitionFactory.class.getCanonicalName());
    KafkaCheckpointLogKeySerde checkpointKeySerde = new KafkaCheckpointLogKeySerde();

    // test that deserialize(serialize(k)) == k
    Assert.assertEquals(keyV2, checkpointKeySerde.fromBytes(checkpointKeySerde.toBytes(keyV2)));
  }

}