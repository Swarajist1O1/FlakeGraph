class DummyClass_134012 {
    @Test
    public void testWriteGauge() {
        Meter.Id id = new Meter.Id("metric", Tags.of("endpoint", "localhost:9000"),
                null, null, Meter.Type.GAUGE);
        LoggingMeterRegistryWithHistogramSupport registry = getInstance();
        Stream<String> stream = registry.writeGauge(id, 20.0);
        String line = stream.findFirst().orElseThrow(IllegalArgumentException::new);
        assertTrue(line.contains("metric,endpoint=localhost:9000,metric_type=gauge value=20"));
    }

}