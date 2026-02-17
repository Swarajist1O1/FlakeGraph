class DummyClass_21010 {
    @Test
    public void testPutInvalidTimestamp() throws Exception {
        final TestServer m = new TestServer(conf);
        m.run();
        try (Socket sock = new Socket("127.0.0.1", 54321);
                PrintWriter writer = new PrintWriter(sock.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));) {
            writer.write("put sys.cpu.user " + TEST_TIME + "Z" + " 1.0 tag1=value1 tag2=value2\n");
            writer.flush();
            sleepUninterruptibly(WAIT_SECONDS, TimeUnit.SECONDS);
            Assert.assertEquals(0, m.getTcpRequests().getCount());
        } finally {
            m.shutdown();
        }
    }

}