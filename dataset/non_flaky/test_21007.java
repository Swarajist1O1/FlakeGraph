class DummyClass_21007 {
    @Test
    public void testPut() throws Exception {
        final TestServer m = new TestServer(conf);
        m.run();
        try (Socket sock = new Socket("127.0.0.1", 54321);
                PrintWriter writer = new PrintWriter(sock.getOutputStream(), true);) {
            writer.write("put sys.cpu.user " + TEST_TIME + " 1.0 tag1=value1 tag2=value2\n");
            writer.flush();
            while (1 != m.getTcpRequests().getCount()) {
                Thread.sleep(5);
            }
            Assert.assertEquals(1, m.getTcpRequests().getResponses().size());
            Assert.assertEquals(MetricRequest.class, m.getTcpRequests().getResponses().get(0).getClass());
            final MetricRequest actual = (MetricRequest) m.getTcpRequests().getResponses().get(0);
            // @formatter:off
            final MetricRequest expected = new MetricRequest(
                    Metric.newBuilder()
                            .name("sys.cpu.user")
                            .value(TEST_TIME, 1.0D)
                            .tag(new Tag("tag1", "value1"))
                            .tag(new Tag("tag2", "value2"))
                            .build()
            );
            // @formatter on
            Assert.assertEquals(expected, actual);
        } finally {
            m.shutdown();
        }
    }

}