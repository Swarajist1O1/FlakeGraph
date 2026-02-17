class DummyClass_84629 {
    @Test
    public void testSystemPropertyValue() {
        String clientName = "zookeeper1";
        System.setProperty(ZK_SASL_CLIENT_USERNAME, clientName);

        ZKClientConfig conf = new ZKClientConfig();
        assertEquals(conf.getProperty(ZK_SASL_CLIENT_USERNAME), clientName);

        String newClientName = "zookeeper2";
        conf.setProperty(ZK_SASL_CLIENT_USERNAME, newClientName);

        assertEquals(conf.getProperty(ZK_SASL_CLIENT_USERNAME), newClientName);
    }

}