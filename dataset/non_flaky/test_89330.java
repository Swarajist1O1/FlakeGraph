class DummyClass_89330 {
  @Test
  public void testCreateCoordinatorStreamWithSpecialCharsInTopicName() {
    final String stream = "test.coordinator_test.Stream";

    Map<String, String> map = new HashMap<>();
    map.put("job.coordinator.segment.bytes", "123");
    map.put("job.coordinator.cleanup.policy", "compact");
    int coordReplicatonFactor = 2;
    map.put(org.apache.samza.config.KafkaConfig.JOB_COORDINATOR_REPLICATION_FACTOR(),
        String.valueOf(coordReplicatonFactor));

    KafkaSystemAdmin admin = Mockito.spy(createSystemAdmin(SYSTEM, map));
    StreamSpec spec = StreamSpec.createCoordinatorStreamSpec(stream, SYSTEM);

    Mockito.doAnswer(invocationOnMock -> {
      StreamSpec internalSpec = (StreamSpec) invocationOnMock.callRealMethod();
      assertTrue(internalSpec instanceof KafkaStreamSpec);  // KafkaStreamSpec is used to carry replication factor
      assertTrue(internalSpec.isCoordinatorStream());
      assertEquals(SYSTEM, internalSpec.getSystemName());
      assertEquals(stream, internalSpec.getPhysicalName());
      assertEquals(1, internalSpec.getPartitionCount());
      Assert.assertEquals(coordReplicatonFactor, ((KafkaStreamSpec) internalSpec).getReplicationFactor());
      Assert.assertEquals("123", ((KafkaStreamSpec) internalSpec).getProperties().getProperty("segment.bytes"));
      // cleanup policy is overridden in the KafkaAdmin
      Assert.assertEquals("compact", ((KafkaStreamSpec) internalSpec).getProperties().getProperty("cleanup.policy"));

      return internalSpec;
    }).when(admin).toKafkaSpec(Mockito.any());

    admin.createStream(spec);
    admin.validateStream(spec);
  }

}