class DummyClass_21008 {
    @Test
    public void testPutMultiple() throws Exception {

        final TestServer m = new TestServer(conf);
        m.run();
        try (Socket sock = new Socket("127.0.0.1", 54321);
                PrintWriter writer = new PrintWriter(sock.getOutputStream(), true)) {
            // @formatter:off
            writer.write("put sys.cpu.user " + TEST_TIME + " 1.0 tag1=value1 tag2=value2\n"
                       + "put sys.cpu.idle " + (TEST_TIME + 1) + " 1.0 tag3=value3 tag4=value4\n");
            writer.flush();
            while (2 != m.getTcpRequests().getCount()) {
                Thread.sleep(5);
            }
            Assert.assertEquals(2, m.getTcpRequests().getResponses().size());
            Assert.assertEquals(MetricRequest.class, m.getTcpRequests().getResponses().get(0).getClass());
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