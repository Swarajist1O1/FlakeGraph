class DummyClass_134981 {
    @Test
    public void testAuthenticatedWebsocket() throws Exception {
        ProgramaticClientEndpoint endpoint = new ProgramaticClientEndpoint();
        ClientEndpointConfig clientEndpointConfig = ClientEndpointConfig.Builder.create().configurator(new ClientConfigurator(){
            @Override
            public void beforeRequest(Map<String, List<String>> headers) {
                headers.put(AUTHORIZATION.toString(), Collections.singletonList(BASIC + " " + FlexBase64.encodeString("user1:password1".getBytes(), false)));
            }

}