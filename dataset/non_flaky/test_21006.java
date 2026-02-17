class DummyClass_21006 {
    @Test
    public void testVersion() throws Exception {
        final TestServer m = new TestServer(conf);
        m.run();
        try (Socket sock = new Socket("127.0.0.1", 54321);
                PrintWriter writer = new PrintWriter(sock.getOutputStream(), true);) {
            writer.write("version\n");
            writer.flush();
            while (1 != m.getTcpRequests().getCount()) {
                Thread.sleep(5);
            }
            Assert.assertEquals(1, m.getTcpRequests().getResponses().size());
            Assert.assertEquals(VersionRequest.class, m.getTcpRequests().getResponses().get(0).getClass());
            VersionRequest v = (VersionRequest) m.getTcpRequests().getResponses().get(0);
            Assert.assertEquals(VersionRequest.VERSION, v.getVersion());
        } finally {
            m.shutdown();
        }
    }

}