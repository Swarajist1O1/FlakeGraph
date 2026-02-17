class DummyClass_134991 {
    @Test
    public void testAnnotatedClientEndpoint() throws Exception {
        AnnotatedClientEndpoint.reset();
        Session session = deployment.connectToServer(AnnotatedClientEndpoint.class, new URI("ws://" + DefaultServer.getHostAddress("default") + ":" + DefaultServer.getHostPort("default") + "/ws/chat/Bob"));

        Assert.assertEquals("hi Bob (protocol=foo)", AnnotatedClientEndpoint.message());

        session.close();
        Assert.assertEquals("CLOSED", AnnotatedClientEndpoint.message());
    }

}