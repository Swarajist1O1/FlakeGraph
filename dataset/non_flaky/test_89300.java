class DummyClass_89300 {
  @Test
  public void testShouldGroupRelevantMonitorConfigTogether() {
    // Test that Monitor Loader groups relevant config together.
    Map<String, String> firstMonitorConfig = ImmutableMap.of("monitor.monitor1.factory.class",
                                                             "org.apache.samza.monitor.DummyMonitor",
                                                             "monitor.monitor1.scheduling.interval.ms",
                                                             "100");
    Map<String, String> secondMonitorConfig = ImmutableMap.of("monitor.monitor2.factory.class",
                                                              "org.apache.samza.monitor.DummyMonitor",
                                                              "monitor.monitor2.scheduling.interval.ms",
                                                              "200");
    MapConfig mapConfig = new MapConfig(ImmutableList.of(firstMonitorConfig, secondMonitorConfig));
    MonitorConfig expectedFirstConfig = new MonitorConfig(new MapConfig(firstMonitorConfig).subset("monitor.monitor1."));
    MonitorConfig expectedSecondConfig = new MonitorConfig(new MapConfig(secondMonitorConfig).subset("monitor.monitor2."));
    Map<String, MonitorConfig> expected = ImmutableMap.of("monitor1", expectedFirstConfig, "monitor2", expectedSecondConfig);
    assertEquals(expected, MonitorConfig.getMonitorConfigs(mapConfig));
  }

}