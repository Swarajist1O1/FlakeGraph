class DummyClass_38669 {
    @Test(expectedExceptions = Exception.class,
    public void openInvalidInfluxConfig() throws Exception {
        InfluxDBGenericRecordSink sink = new InfluxDBGenericRecordSink();
        sink.open(new HashMap<>(), mock(SinkContext.class));
    }

}