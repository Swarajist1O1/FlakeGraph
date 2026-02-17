class DummyClass_98072 {
  @Test
  public void testServerSettings() {
    long heartbeatFrequencyMS = 1234;
    long minHeartbeatFrequencyMS = heartbeatFrequencyMS / 2;
    JsonObject config = new JsonObject();
    config.put("heartbeatFrequencyMS", heartbeatFrequencyMS);
    config.put("minHeartbeatFrequencyMS", minHeartbeatFrequencyMS);

    ServerSettings settings = new ServerSettingsParser(config).settings();
    assertEquals(heartbeatFrequencyMS, settings.getHeartbeatFrequency(TimeUnit.MILLISECONDS));
    assertEquals(minHeartbeatFrequencyMS, settings.getMinHeartbeatFrequency(TimeUnit.MILLISECONDS));
  }

}