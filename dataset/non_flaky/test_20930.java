class DummyClass_20930 {
    @Test
    public void testJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String expectedJson = "{\"name\":\"m1\",\"timestamp\":1,\"measure\":1.0,\"tags\":[{\"k1\":\"v1\"}]}";

        Metric m1 = Metric.newBuilder().name("m1").tag("k1", "v1").value(1, 1.0).build();

        Metric expectedMetric = mapper.readValue(expectedJson, Metric.class);

        assertTrue(m1.equals(expectedMetric));

        expectedJson = "{\"name\":\"m1\",\"tags\":[{\"k1\":\"v1\"}],\"timestamp\":5,\"measure\":5.0}";
        expectedMetric = mapper.readValue(expectedJson, Metric.class);

        assertEquals((long) expectedMetric.getValue().getTimestamp(), 5L);
        assertEquals(expectedMetric.getValue().getMeasure(), 5.0D, 0.0);

    }

}