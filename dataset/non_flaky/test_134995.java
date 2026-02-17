class DummyClass_134995 {
    @Test
    public void testErrorHandling() throws Exception {
        //make a sub class
        AnnotatedClientEndpoint c = new AnnotatedClientEndpoint() {

        };

        Session session = deployment.connectToServer(c, new URI("ws://" + DefaultServer.getHostAddress("default") + ":" + DefaultServer.getHostPort("default") + "/ws/error"));
        Assert.assertEquals("hi", ErrorEndpoint.getMessage());
        session.getAsyncRemote().sendText("app-error");
        Assert.assertEquals("app-error", ErrorEndpoint.getMessage());
        Assert.assertEquals("ERROR: java.lang.RuntimeException", ErrorEndpoint.getMessage());
        Assert.assertTrue(c.isOpen());

        session.getBasicRemote().sendText("io-error");
        Assert.assertEquals("io-error", ErrorEndpoint.getMessage());
        Assert.assertEquals("ERROR: java.io.IOException", ErrorEndpoint.getMessage());
        Assert.assertTrue(c.isOpen());
        ((UndertowSession)session).forceClose();
        Assert.assertEquals("CLOSED", ErrorEndpoint.getMessage());

    }

}