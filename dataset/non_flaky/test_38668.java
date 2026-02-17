class DummyClass_38668 {
    @Test
    public void openInfluxV2() throws Exception {
        Map<String, Object> map = new HashMap();
        map.put("influxdbUrl", "http://localhost:9999");
        map.put("token", "xxxx");
        map.put("organization", "example-org");
        map.put("bucket", "example-bucket");

        InfluxDBGenericRecordSink sink = new InfluxDBGenericRecordSink();
        try {
            sink.open(map, mock(SinkContext.class));
        } catch (InfluxDBIOException e) {
            // Do nothing
        }
        assertTrue(sink.sink instanceof InfluxDBSink);
    }

}