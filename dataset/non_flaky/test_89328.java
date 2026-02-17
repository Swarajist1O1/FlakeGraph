class DummyClass_89328 {
  @Test
  public void testToKafkaSpec() {
    String topicName = "testStream";

    int defaultPartitionCount = 2;
    int changeLogPartitionFactor = 5;
    Map<String, String> map = new HashMap<>();
    Config config = new MapConfig(map);
    StreamSpec spec = new StreamSpec("id", topicName, SYSTEM, defaultPartitionCount, config);

    KafkaSystemAdmin kafkaAdmin = systemAdmin();
    KafkaStreamSpec kafkaSpec = kafkaAdmin.toKafkaSpec(spec);

    Assert.assertEquals("id", kafkaSpec.getId());
    Assert.assertEquals(topicName, kafkaSpec.getPhysicalName());
    Assert.assertEquals(SYSTEM, kafkaSpec.getSystemName());
    Assert.assertEquals(defaultPartitionCount, kafkaSpec.getPartitionCount());

    // validate that conversion is using coordination metadata
    map.put("job.coordinator.segment.bytes", "123");
    map.put("job.coordinator.cleanup.policy", "superCompact");
    int coordReplicatonFactor = 4;
    map.put(org.apache.samza.config.KafkaConfig.JOB_COORDINATOR_REPLICATION_FACTOR(),
        String.valueOf(coordReplicatonFactor));

    KafkaSystemAdmin admin = Mockito.spy(createSystemAdmin(SYSTEM, map));
    spec = StreamSpec.createCoordinatorStreamSpec(topicName, SYSTEM);
    kafkaSpec = admin.toKafkaSpec(spec);
    Assert.assertEquals(coordReplicatonFactor, kafkaSpec.getReplicationFactor());
    Assert.assertEquals("123", kafkaSpec.getProperties().getProperty("segment.bytes"));
    // cleanup policy is overridden in the KafkaAdmin
    Assert.assertEquals("compact", kafkaSpec.getProperties().getProperty("cleanup.policy"));

    // validate that conversion is using changeLog metadata
    map = new HashMap<>();
    map.put(JobConfig.JOB_DEFAULT_SYSTEM, SYSTEM);

    map.put(String.format("stores.%s.changelog", "fakeStore"), topicName);
    int changeLogReplicationFactor = 3;
    map.put(String.format("stores.%s.changelog.replication.factor", "fakeStore"),
        String.valueOf(changeLogReplicationFactor));
    admin = Mockito.spy(createSystemAdmin(SYSTEM, map));
    spec = StreamSpec.createChangeLogStreamSpec(topicName, SYSTEM, changeLogPartitionFactor);
    kafkaSpec = admin.toKafkaSpec(spec);
    Assert.assertEquals(changeLogReplicationFactor, kafkaSpec.getReplicationFactor());

    // same, but with missing topic info
    try {
      admin = Mockito.spy(createSystemAdmin(SYSTEM, map));
      spec = StreamSpec.createChangeLogStreamSpec("anotherTopic", SYSTEM, changeLogPartitionFactor);
      kafkaSpec = admin.toKafkaSpec(spec);
      Assert.fail("toKafkaSpec should've failed for missing topic");
    } catch (StreamValidationException e) {
      // expected
    }

    // validate that conversion is using intermediate streams properties
    String interStreamId = "isId";

    Map<String, String> interStreamMap = new HashMap<>();
    interStreamMap.put("app.mode", ApplicationConfig.ApplicationMode.BATCH.toString());
    interStreamMap.put(String.format("streams.%s.samza.intermediate", interStreamId), "true");
    interStreamMap.put(String.format("streams.%s.samza.system", interStreamId), "testSystem");
    interStreamMap.put(String.format("streams.%s.p1", interStreamId), "v1");
    interStreamMap.put(String.format("streams.%s.retention.ms", interStreamId), "123");
    // legacy format
    interStreamMap.put(String.format("systems.%s.streams.%s.p2", "testSystem", interStreamId), "v2");

    admin = Mockito.spy(createSystemAdmin(SYSTEM, interStreamMap));
    spec = new StreamSpec(interStreamId, topicName, SYSTEM, defaultPartitionCount, config);
    kafkaSpec = admin.toKafkaSpec(spec);
    Assert.assertEquals("v1", kafkaSpec.getProperties().getProperty("p1"));
    Assert.assertEquals("v2", kafkaSpec.getProperties().getProperty("p2"));
    Assert.assertEquals("123", kafkaSpec.getProperties().getProperty("retention.ms"));
    Assert.assertEquals(defaultPartitionCount, kafkaSpec.getPartitionCount());
  }

}