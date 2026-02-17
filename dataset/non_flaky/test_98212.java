class DummyClass_98212 {
    @Test
    public void testFromServiceParameterToServiceParameters() {
        Map<String, String> serviceParameters = new HashMap<>();
        serviceParameters.put(ConnectionOptions.PARAM_USE_SYSTEM_PROPERTIES, "true");
        serviceParameters.put(ConnectionOptions.PARAM_MAX_CONNECTIONS, "10");
        serviceParameters.put(ConnectionOptions.PARAM_ALLOW_SELF_SIGNED_CERTIFICATES, "true");
        serviceParameters.put(ConnectionOptions.PARAM_DISABLE_HOSTNAME_VERIFICATION, "true");
        serviceParameters.put(ConnectionOptions.PARAM_CONNECTION_TIMEOUT_MS, "100");
        serviceParameters.put(ConnectionOptions.PARAM_REQUEST_TIMEOUT_MS, "200");
        serviceParameters.put(ConnectionOptions.PARAM_SOCKET_TIMEOUT_MS, "300");
        serviceParameters.put(ConnectionOptions.PARAM_PROXY_HOST, "somehost");
        serviceParameters.put(ConnectionOptions.PARAM_PROXY_PORT, "111");
        serviceParameters.put(ConnectionOptions.PARAM_PROXY_USERNAME, "user");
        serviceParameters.put(ConnectionOptions.PARAM_PROXY_PASSWORD, "password");
        Assert.assertEquals(serviceParameters, ConnectionOptions.fromServiceFactoryParameters(serviceParameters).toServiceFactoryParameters());
    }

}