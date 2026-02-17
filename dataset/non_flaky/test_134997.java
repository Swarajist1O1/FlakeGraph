class DummyClass_134997 {
    @Test
    public void testGenericMessageHandling() throws Exception {
        //make a sub class
        AnnotatedGenericClientEndpoint c = new AnnotatedGenericClientEndpoint() {

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