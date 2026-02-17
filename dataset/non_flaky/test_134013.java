class DummyClass_134013 {
    @Test
    public void testWriteCounter() {
        Meter.Id id = new Meter.Id("metric", Tags.of("endpoint", "localhost:9000"),
                null, null,
                Meter.Type.COUNTER);
        LoggingMeterRegistryWithHistogramSupport registry = getInstance();
        Stream<String> stream = registry.writeCounter(id, 30);
        String line = stream.findFirst().orElseThrow(IllegalArgumentException::new);
        assertTrue(line.contains("metric,endpoint=localhost:9000,metric_type=counter value=30"));
    }

}