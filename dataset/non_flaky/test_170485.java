class DummyClass_170485 {
    @Test
    public void testAddressAfterStart() throws Exception
    {
        connectorServer = new ConnectorServer(new JMXServiceURL("service:jmx:rmi:///jndi/rmi:///jmxrmi"), objectName);
        connectorServer.start();

        JMXServiceURL address = connectorServer.getAddress();
        assertTrue(address.toString().matches("service:jmx:rmi://[^:]+:\\d+/jndi/rmi://[^:]+:\\d+/jmxrmi"));
    }

}