class DummyClass_89301 {
  @Test
  public void testMonitorExceptionIsolation() {
    // Test that an exception from a monitor doesn't bubble up out of the scheduler.
    Map<String, String> configMap =
        ImmutableMap.of(String.format("monitor.name.%s", CONFIG_MONITOR_FACTORY_CLASS),
                        ExceptionThrowingMonitorFactory.class.getCanonicalName());
    SamzaRestConfig config = new SamzaRestConfig(new MapConfig(configMap));
    SamzaMonitorService monitorService = new SamzaMonitorService(config,
                                                                 METRICS_REGISTRY);

    // This will throw if the exception isn't caught within the provider.
    monitorService.start();
    monitorService.stop();
  }

}