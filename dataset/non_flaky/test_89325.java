class DummyClass_89325 {
  @Test
  public void testCreateStreamShouldCoordinatorStreamWithCorrectTopicProperties() throws Exception {
    String coordinatorTopicName = String.format("topic-name-%s", RandomStringUtils.randomAlphabetic(5));
    StreamSpec coordinatorStreamSpec = KafkaStreamSpec.createCoordinatorStreamSpec(coordinatorTopicName, SYSTEM());

    boolean hasCreatedStream = systemAdmin().createStream(coordinatorStreamSpec);

    assertTrue(hasCreatedStream);

    Map<String, String> coordinatorTopicProperties = getTopicConfigFromKafkaBroker(coordinatorTopicName);

    assertEquals("compact", coordinatorTopicProperties.get(TopicConfig.CLEANUP_POLICY_CONFIG));
    assertEquals("26214400", coordinatorTopicProperties.get(TopicConfig.SEGMENT_BYTES_CONFIG));
    assertEquals("86400000", coordinatorTopicProperties.get(TopicConfig.DELETE_RETENTION_MS_CONFIG));
  }

}