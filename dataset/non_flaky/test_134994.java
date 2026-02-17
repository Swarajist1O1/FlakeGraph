class DummyClass_134994 {
    @Test
    public void testAnnotatedClientEndpointWithConfigurator() throws Exception {


        Session session = deployment.connectToServer(AnnotatedClientEndpointWithConfigurator.class, new URI("ws://" + DefaultServer.getHostAddress("default") + ":" + DefaultServer.getHostPort("default") + "/ws/chat/Bob"));

        Assert.assertEquals("hi Bob (protocol=configured-proto)", AnnotatedClientEndpointWithConfigurator.message());
        Assert.assertEquals("foo, bar, configured-proto", ClientConfigurator.sentSubProtocol);
        Assert.assertEquals("configured-proto", ClientConfigurator.receivedSubProtocol());

        session.close();
        Assert.assertEquals("CLOSED", AnnotatedClientEndpointWithConfigurator.message());
    }

}