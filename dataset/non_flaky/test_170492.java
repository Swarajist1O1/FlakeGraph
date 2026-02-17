class DummyClass_170492 {
    @Test
    public void testLocalhostRMIBindsToLoopback() throws Exception
    {
        connectorServer = new ConnectorServer(new JMXServiceURL("service:jmx:rmi://localhost/jndi/rmi://localhost:1099/jmxrmi"), objectName);
        connectorServer.start();
        JMXServiceURL address = connectorServer.getAddress();

        InetAddress localHost = InetAddress.getLocalHost();
        if (!localHost.isLoopbackAddress())
        {
            assertThrows(ConnectException.class, () ->
            {
                // Verify that I cannot connect to the RMIRegistry using a non-loopback address.
                new Socket(localHost, address.getPort());
            });
        }

        InetAddress loopback = InetAddress.getLoopbackAddress();
        new Socket(loopback, address.getPort()).close();
    }

}