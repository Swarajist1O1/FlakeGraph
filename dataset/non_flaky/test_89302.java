class DummyClass_89302 {
  @Test
  public void testShouldNotFailWhenTheMonitorFactoryClassIsNotDefined()
      throws Exception {
    // Test that when MonitorFactoryClass is not defined in the config, monitor service
    // should not fail.
    Map<String, String> configMap = ImmutableMap.of("monitor.monitor1.config.key1", "configValue1",
                                                    "monitor.monitor1.config.key2", "configValue2",
                                                    String.format("monitor.MOCK_MONITOR.%s", CONFIG_MONITOR_FACTORY_CLASS),
                                                    MockMonitorFactory.class.getCanonicalName());

    SamzaRestConfig config = new SamzaRestConfig(new MapConfig(configMap));

    class SamzaMonitorServiceTest extends SamzaMonitorService {
      MetricsRegistry metricsRegistry;
      public SamzaMonitorServiceTest(SamzaRestConfig config, MetricsRegistry metricsRegistry) {
        super(config, metricsRegistry);
        this.metricsRegistry = metricsRegistry;
      }

      @Override
      public void createSchedulerAndScheduleMonitor(String monitorName, MonitorConfig monitorConfig, long schedulingIntervalInMs) {
        try {
          // immediately run monitor, without scheduling
          instantiateMonitor(monitorName, monitorConfig, metricsRegistry).monitor();
        } catch (Exception e) {
          fail();
        }
      }

}