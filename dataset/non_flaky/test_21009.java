class DummyClass_21009 {
    @Test
    public void testPutMultipleBinary() throws Exception {

        FlatBufferBuilder builder = new FlatBufferBuilder(1);

        int[] metric = new int[2];
        Map<String, String> t = new HashMap<>();
        t.put("tag1", "value1");
        t.put("tag2", "value2");
        metric[0] = createMetric(builder, "sys.cpu.user", TEST_TIME, 1.0D, t);
        t = new HashMap<>();
        t.put("tag3", "value3");
        t.put("tag4", "value4");
        metric[1] = createMetric(builder, "sys.cpu.idle", TEST_TIME + 1, 1.0D, t);

        int metricVector = timely.api.flatbuffer.Metrics.createMetricsVector(builder, metric);

        timely.api.flatbuffer.Metrics.startMetrics(builder);
        timely.api.flatbuffer.Metrics.addMetrics(builder, metricVector);
        int metrics = timely.api.flatbuffer.Metrics.endMetrics(builder);
        timely.api.flatbuffer.Metrics.finishMetricsBuffer(builder, metrics);

        ByteBuffer binary = builder.dataBuffer();
        byte[] data = new byte[binary.remaining()];
        binary.get(data, 0, binary.remaining());
        LOG.debug("Sending {} bytes", data.length);

        final TestServer m = new TestServer(conf);
        m.run();
        try (Socket sock = new Socket("127.0.0.1", 54321);) {
            sock.getOutputStream().write(data);
            sock.getOutputStream().flush();
            while (2 != m.getTcpRequests().getCount()) {
                LOG.debug("Thread sleeping");
                Thread.sleep(5);
            }
            Assert.assertEquals(2, m.getTcpRequests().getResponses().size());
            Assert.assertEquals(MetricRequest.class, m.getTcpRequests().getResponses().get(0).getClass());
            // @formatter:off
            MetricRequest actual = (MetricRequest) m.getTcpRequests().getResponses().get(0);
            MetricRequest expected = new MetricRequest(
                    Metric.newBuilder()
                            .name("sys.cpu.user")
                            .value(TEST_TIME, 1.0D)
                            .tag(new Tag("tag1", "value1"))
                            .tag(new Tag("tag2", "value2"))
                            .build()
            );
            Assert.assertEquals(expected, actual);

            Assert.assertEquals(MetricRequest.class, m.getTcpRequests().getResponses().get(1).getClass());
            actual = (MetricRequest) m.getTcpRequests().getResponses().get(1);
            expected = new MetricRequest(
                    Metric.newBuilder()
                            .name("sys.cpu.idle")
                            .value(TEST_TIME + 1, 1.0D)
                            .tag(new Tag("tag3", "value3"))
                            .tag(new Tag("tag4", "value4"))
                            .build()
            );
            // @formatter:on
            Assert.assertEquals(expected, actual);

        } finally {
            m.shutdown();
        }
    }

}