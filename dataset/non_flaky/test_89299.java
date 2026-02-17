class DummyClass_89299 {
  @Test
  public void testMonitorsShouldBeInstantiatedProperly() {
    // Test that a monitor should be instantiated properly by invoking
    // the appropriate factory method.
    Map<String, String> configMap = ImmutableMap.of(CONFIG_MONITOR_FACTORY_CLASS,
                                                    DummyMonitorFactory.class.getCanonicalName());
    Monitor monitor = null;
    try {
      monitor = MonitorLoader.instantiateMonitor("testMonitor", new MonitorConfig(new MapConfig(configMap)),
          METRICS_REGISTRY);
    } catch (InstantiationException e) {
      fail();
    }
    assertNotNull(monitor);
    // Object should implement the monitor().
    try {
      monitor.monitor();
    } catch (Exception e) {
      fail();
    }
  }

}