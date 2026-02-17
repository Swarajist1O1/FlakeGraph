class DummyClass_162584 {
  @Test
  public void test() {
    SystemMetrics.registerObservers();

    waitAndAssertMetrics(
        metric ->
            metric
                .hasName("system.memory.usage")
                .hasUnit("By")
                .hasLongGauge()
                .points()
                .anySatisfy(point -> assertThat(point.getValue()).isPositive()),
        metric ->
            metric
                .hasName("system.memory.utilization")
                .hasUnit("1")
                .hasDoubleGauge()
                .points()
                .anySatisfy(point -> assertThat(point.getValue()).isPositive()),
        metric -> metric.hasName("system.network.io").hasUnit("By").hasLongGauge(),
        metric -> metric.hasName("system.network.packets").hasUnit("packets").hasLongGauge(),
        metric -> metric.hasName("system.network.errors").hasUnit("errors").hasLongGauge(),
        metric -> metric.hasName("system.disk.operations").hasUnit("operations").hasLongGauge());
  }

}