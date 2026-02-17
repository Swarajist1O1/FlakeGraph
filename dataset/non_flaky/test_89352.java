class DummyClass_89352 {
  @Test
  public void testGetCheckpointTopicProperties() {
    Map<String, String> config = new HashMap<>();
    Properties properties = new KafkaConfig(new MapConfig(config)).getCheckpointTopicProperties();

    assertEquals(properties.getProperty("cleanup.policy"), "compact");
    assertEquals(properties.getProperty("segment.bytes"), String.valueOf(KafkaConfig.DEFAULT_CHECKPOINT_SEGMENT_BYTES()));

    config.put(ApplicationConfig.APP_MODE, ApplicationConfig.ApplicationMode.BATCH.name());
    properties = new KafkaConfig(new MapConfig(config)).getCheckpointTopicProperties();

    assertEquals(properties.getProperty("cleanup.policy"), "compact,delete");
    assertEquals(properties.getProperty("segment.bytes"), String.valueOf(KafkaConfig.DEFAULT_CHECKPOINT_SEGMENT_BYTES()));
    assertEquals(properties.getProperty("retention.ms"), String.valueOf(KafkaConfig.DEFAULT_RETENTION_MS_FOR_BATCH()));
  }

}