class DummyClass_89351 {
  @Test
  public void testGetIntermediateStreamProperties() {
    Map<String, String> config = new HashMap<>();
    KafkaSystemFactory factory = new KafkaSystemFactory();
    Map<String, Properties> properties = JavaConversions.mapAsJavaMap(
        factory.getIntermediateStreamProperties(new MapConfig(config)));
    assertTrue(properties.isEmpty());

    // no properties for stream
    config.put("streams.test.samza.intermediate", "true");
    config.put("streams.test.compression.type", "lz4"); //some random config
    properties = JavaConversions.mapAsJavaMap(
        factory.getIntermediateStreamProperties(new MapConfig(config)));
    assertTrue(properties.isEmpty());

    config.put(ApplicationConfig.APP_MODE, ApplicationConfig.ApplicationMode.BATCH.name());

    KafkaSystemAdmin admin = createSystemAdmin(SYSTEM(), config);
    StreamSpec spec = new StreamSpec("test", "test", SYSTEM(),
        Collections.singletonMap("replication.factor", "1"));
    KafkaStreamSpec kspec = admin.toKafkaSpec(spec);

    Properties prop = kspec.getProperties();
    assertEquals(prop.getProperty("retention.ms"), String.valueOf(KafkaConfig.DEFAULT_RETENTION_MS_FOR_BATCH()));
    assertEquals(prop.getProperty("compression.type"), "lz4");

    // replication.factor should be removed from the properties and set on the spec directly
    assertEquals(kspec.getReplicationFactor(), 1);
    assertNull(prop.getProperty("replication.factor"));
  }

}