class DummyClass_134982 {
    @Test
    public void testWrappedRequest() throws Exception {
        ProgramaticClientEndpoint endpoint = new ProgramaticClientEndpoint();
        ClientEndpointConfig clientEndpointConfig = ClientEndpointConfig.Builder.create().build();
        ContainerProvider.getWebSocketContainer().connectToServer(endpoint, clientEndpointConfig, new URI("ws://" + DefaultServer.getHostAddress("default") + ":" + DefaultServer.getHostPort("default") + "/servletContext/wrapper"));
        Assert.assertEquals("wrapped", endpoint.getResponses().poll(15, TimeUnit.SECONDS));
        endpoint.session.close();
        endpoint.closeLatch.await(10, TimeUnit.SECONDS);
    }

}