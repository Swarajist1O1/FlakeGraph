class DummyClass_134989 {
    @Test
    public void testRedirectHandling() throws Exception {
        AnnotatedClientEndpoint.reset();
        Session session = deployment.connectToServer(AnnotatedClientEndpoint.class, new URI("ws://" + DefaultServer.getHostAddress("default") + ":" + DefaultServer.getHostPort("default") + "/ws/redirect"));

        Assert.assertEquals("hi Stuart (protocol=foo)", AnnotatedClientEndpoint.message());

        session.close();
        Assert.assertEquals("CLOSED", AnnotatedClientEndpoint.message());
    }

}