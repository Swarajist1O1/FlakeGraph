class DummyClass_84628 {
    @Test
    public void testDefaultConfiguration() {
        Map<String, String> properties = new HashMap<>();
        properties.put(ZK_SASL_CLIENT_USERNAME, "zookeeper1");
        properties.put(LOGIN_CONTEXT_NAME_KEY, "Client1");
        properties.put(ENABLE_CLIENT_SASL_KEY, "true");
        properties.put(ZOOKEEPER_SERVER_REALM, "zookeeper/hadoop.hadoop.com");
        properties.put(DISABLE_AUTO_WATCH_RESET, "true");
        properties.put(ZOOKEEPER_CLIENT_CNXN_SOCKET, "ClientCnxnSocketNetty");
        properties.put(SECURE_CLIENT, "true");

        for (Map.Entry<String, String> e : properties.entrySet()) {
            System.setProperty(e.getKey(), e.getValue());
        }
        /**
         * ZKClientConfig should get initialized with system properties
         */
        ZKClientConfig conf = new ZKClientConfig();
        for (Map.Entry<String, String> e : properties.entrySet()) {
            assertEquals(e.getValue(), conf.getProperty(e.getKey()));
        }
        /**
         * clear properties
         */
        for (Map.Entry<String, String> e : properties.entrySet()) {
            System.clearProperty(e.getKey());
        }

        conf = new ZKClientConfig();
        /**
         * test that all the properties are null
         */
        for (Map.Entry<String, String> e : properties.entrySet()) {
            String result = conf.getProperty(e.getKey());
            assertNull(result);
        }
    }

}