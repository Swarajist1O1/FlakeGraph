class DummyClass_134015 {
    @Test
    public void testWriteSummary() {
        LoggingMeterRegistryWithHistogramSupport registry = getInstance();
        TestSummary summary = new TestSummary();
        String line = registry.writeSummary(summary).findFirst().orElseThrow(IllegalArgumentException::new);
        assertTrue(line.contains("metric,endpoint=localhost:9000,metric_type=summary sum=200,count=100,mean=2,upper=300"));
    }

}