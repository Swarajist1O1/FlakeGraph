class DummyClass_38608 {
    @Test(timeOut = 5000)
    public void testLoadArguments() throws Exception {
        PerformanceClient client = new PerformanceClient();

        // "--proxy-url" has the highest priority
        PerformanceClient.Arguments arguments = client.loadArguments(
                getArgs("ws://broker0.pulsar.apache.org:8080/", "./src/test/resources/websocket_client1.conf"));
        assertEquals(arguments.proxyURL, "ws://broker0.pulsar.apache.org:8080/");

        // "webSocketServiceUrl" written in the conf file has the second priority
        arguments = client.loadArguments(getArgs(null, "./src/test/resources/websocket_client1.conf"));
        assertEquals(arguments.proxyURL, "ws://broker1.pulsar.apache.org:8080/");

        // "webServiceUrl" written in the conf file has the third priority
        arguments = client.loadArguments(getArgs(null, "./src/test/resources/websocket_client2.conf"));
        assertEquals(arguments.proxyURL, "ws://broker2.pulsar.apache.org:8080/");

        // "serviceUrl" written in the conf file has the fourth priority
        arguments = client.loadArguments(getArgs(null, "./src/test/resources/websocket_client3.conf"));
        assertEquals(arguments.proxyURL, "wss://broker3.pulsar.apache.org:8443/");

        // The default value is "ws://localhost:8080/"
        arguments = client.loadArguments(getArgs(null, null));
        assertEquals(arguments.proxyURL, "ws://localhost:8080/");

        // If the URL does not end with "/", it will be added
        arguments = client.loadArguments(getArgs("ws://broker0.pulsar.apache.org:8080", null));
        assertEquals(arguments.proxyURL, "ws://broker0.pulsar.apache.org:8080/");
    }

}