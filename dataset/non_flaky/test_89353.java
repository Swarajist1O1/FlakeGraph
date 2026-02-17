class DummyClass_89353 {
  @Test
  public void testBinaryCompatibility() {
    KafkaCheckpointLogKey logKey1 = new KafkaCheckpointLogKey(KafkaCheckpointLogKey.CHECKPOINT_V1_KEY_TYPE,
        new TaskName("Partition 0"), GroupByPartitionFactory.class.getCanonicalName());
    KafkaCheckpointLogKeySerde checkpointSerde = new KafkaCheckpointLogKeySerde();

    byte[] bytes = ("{\"systemstreampartition-grouper-factory\"" +
        ":\"org.apache.samza.container.grouper.stream.GroupByPartitionFactory\",\"taskName\":\"Partition 0\"," +
        "\"type\":\"checkpoint\"}").getBytes();

    // test that the checkpoints returned by the Serde are byte-wise identical to an actual checkpoint in Kafka
    Assert.assertEquals(true, Arrays.equals(bytes, checkpointSerde.toBytes(logKey1)));
  }

}