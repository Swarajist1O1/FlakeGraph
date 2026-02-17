class DummyClass_134014 {
    @Test
    public void testWriteTimer() {
        LoggingMeterRegistryWithHistogramSupport registry = getInstance();
        Timer timer = new TestTimer();
        String line = registry.writeTimer(timer).findFirst().orElseThrow(IllegalArgumentException::new);
        assertTrue(line.contains("metric,endpoint=localhost:9000,metric_type=timer sum=200,count=100,mean=2,upper=300"));
    }

}