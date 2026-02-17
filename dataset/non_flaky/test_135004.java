class DummyClass_135004 {
    @Test
    public void testThreadSafeSend() throws Exception {
        AnnotatedClientEndpoint.reset();
        Session session = deployment.connectToServer(AnnotatedClientEndpoint.class, new URI("ws://" + DefaultServer.getHostAddress("default") + ":" + DefaultServer.getHostPort("default") + "/ws/threads"));
        Set<String> expected = ThreadSafetyEndpoint.expected();
        long end = System.currentTimeMillis() + 10000;
        while (!expected.isEmpty() && System.currentTimeMillis() < end) {
            expected.remove(AnnotatedClientEndpoint.message());
        }
        session.close();
        Assert.assertEquals(0, expected.size());
    }

}