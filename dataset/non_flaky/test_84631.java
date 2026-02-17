class DummyClass_84631 {
    @Test
    public void testSetConfiguration() {
        ZKClientConfig conf = new ZKClientConfig();
        String defaultValue = conf.getProperty(ZKClientConfig.ENABLE_CLIENT_SASL_KEY, ZKClientConfig.ENABLE_CLIENT_SASL_DEFAULT);
        if (defaultValue.equals("true")) {
            conf.setProperty(ENABLE_CLIENT_SASL_KEY, "false");
        } else {
            conf.setProperty(ENABLE_CLIENT_SASL_KEY, "true");
        }
        assertTrue(conf.getProperty(ENABLE_CLIENT_SASL_KEY) != defaultValue);
    }

}