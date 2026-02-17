class DummyClass_38667 {
    @Test
    public void openInfluxV1() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("influxdbUrl", "http://localhost:8086");
        map.put("database", "test_db");

        InfluxDBGenericRecordSink sink = new InfluxDBGenericRecordSink();
        try {
            sink.open(map, mock(SinkContext.class));
        } catch (InfluxDBIOException e) {
            // Do nothing
        }
        assertTrue(sink.sink instanceof org.apache.pulsar.io.influxdb.v1.InfluxDBGenericRecordSink);
    }

}