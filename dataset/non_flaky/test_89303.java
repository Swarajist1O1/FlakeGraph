class DummyClass_89303 {
  @Test(expected = SamzaException.class)
  public void testShouldFailWhenTheMonitorFactoryClassIsInvalid() {
    // Test that when MonitorFactoryClass is defined in the config and is invalid,
    // monitor service should fail. Should throw back SamzaException.
    Map<String, String> configMap = ImmutableMap.of(String.format("monitor.name.%s", CONFIG_MONITOR_FACTORY_CLASS),
                                                    "RandomClassName");
    SamzaRestConfig config = new SamzaRestConfig(new MapConfig(configMap));
    SamzaMonitorService monitorService = new SamzaMonitorService(config,
                                                                 METRICS_REGISTRY);
    monitorService.start();
  }

}