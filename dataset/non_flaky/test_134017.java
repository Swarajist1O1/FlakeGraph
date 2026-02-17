class DummyClass_134017 {
    @Test
    public void testSummaryPercentiles() {
        AggregateSink sink = new AggregateSink();

        LoggingMeterRegistryWithHistogramSupport registry = getInstance(sink);

        DistributionSummary summary = DistributionSummary.builder("summary")
                .publishPercentileHistogram()
                .publishPercentiles(0.99, 0.95, 0.5)
                .tags("endpoint", "localhost:9000")
                .register(registry);

        for (int i = 0; i < 3; i++) {
            summary.record(100);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {

            }
        }
        assertTrue(sink.substringIsPresent("summary_percentile,endpoint=localhost:9000,phi=0.99,metric_type=gauge value=100"));
        assertTrue(sink.substringIsPresent("summary_percentile,endpoint=localhost:9000,phi=0.95,metric_type=gauge value=100"));
        assertTrue(sink.substringIsPresent("summary_percentile,endpoint=localhost:9000,phi=0.5,metric_type=gauge value=100"));
    }

}