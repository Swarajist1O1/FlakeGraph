class DummyClass_162583 {
  @Test
  public void test() {
    ProcessMetrics.registerObservers();

    waitAndAssertMetrics(
        metric ->
            metric
                .hasName("runtime.java.memory")
                .hasUnit("bytes")
                .hasLongGauge()
                .points()
                .anySatisfy(point -> assertThat(point.getValue()).isPositive()),
        metric ->
            metric
                .hasName("runtime.java.cpu_time")
                .hasUnit("seconds")
                .hasDoubleGauge()
                .points()
                .anySatisfy(point -> assertThat(point.getValue()).isPositive()));
  }

}