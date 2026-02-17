class DummyClass_84630 {
    @Test
    public void testReadConfigurationFile() throws IOException, ConfigException {
        File file = File.createTempFile("clientConfig", ".conf", testData);
        file.deleteOnExit();
        Properties clientConfProp = new Properties();
        clientConfProp.setProperty(ENABLE_CLIENT_SASL_KEY, "true");
        clientConfProp.setProperty(ZK_SASL_CLIENT_USERNAME, "ZK");
        clientConfProp.setProperty(LOGIN_CONTEXT_NAME_KEY, "MyClient");
        clientConfProp.setProperty(ZOOKEEPER_SERVER_REALM, "HADOOP.COM");
        clientConfProp.setProperty("dummyProperty", "dummyValue");
        OutputStream io = new FileOutputStream(file);
        try {
            clientConfProp.store(io, "Client Configurations");
        } finally {
            io.close();
        }

        ZKClientConfig conf = new ZKClientConfig();
        conf.addConfiguration(file.getAbsolutePath());
        assertEquals(conf.getProperty(ENABLE_CLIENT_SASL_KEY), "true");
        assertEquals(conf.getProperty(ZK_SASL_CLIENT_USERNAME), "ZK");
        assertEquals(conf.getProperty(LOGIN_CONTEXT_NAME_KEY), "MyClient");
        assertEquals(conf.getProperty(ZOOKEEPER_SERVER_REALM), "HADOOP.COM");
        assertEquals(conf.getProperty("dummyProperty"), "dummyValue");

        // try to delete it now as we have done with the created file, why to
        // wait for deleteOnExit() deletion
        file.delete();
    }

}