class DummyClass_134016 {
    @Test
    public void testTimerPercentiles() {
        AggregateSink sink = new AggregateSink();

        LoggingMeterRegistryWithHistogramSupport registry = getInstance(sink);

        Timer timer = Timer.builder("timer")
                .publishPercentileHistogram()
                .publishPercentiles(0.99, 0.95, 0.5)
                .tags("endpoint", "localhost:9000")
                .register(registry);
        for (int i = 0; i < 3; i++) {
            timer.record(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {

                }
            });
        }
        assertTrue(sink.substringIsPresent("timer_percentile,endpoint=localhost:9000,phi=0.99,metric_type=gauge"));
        assertTrue(sink.substringIsPresent("timer_percentile,endpoint=localhost:9000,phi=0.95,metric_type=gauge"));
        assertTrue(sink.substringIsPresent("timer_percentile,endpoint=localhost:9000,phi=0.5,metric_type=gauge"));
    }

}