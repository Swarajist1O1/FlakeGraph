class DummyClass_53132 {
    @Test
    public void test_delta_method() {
        String name = "metric.name";
        assertEquals(5l, uaaMetricsEmitter.getMetricDelta(name, 5l));
        assertEquals(0l, uaaMetricsEmitter.getMetricDelta(name, 5l));
        assertEquals(3l, uaaMetricsEmitter.getMetricDelta(name, 8l));
    }

}