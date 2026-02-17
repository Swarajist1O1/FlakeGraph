class DummyClass_70782 {
    @Test
    public void testCreateWithAllowedOverridesForAllPolicy() throws Exception {
        // setup up props for the sink connector
        Map<String, String> props = basicConnectorConfig();
        props.put(ConnectorConfig.CONNECTOR_CLIENT_CONSUMER_OVERRIDES_PREFIX + CommonClientConfigs.CLIENT_ID_CONFIG, "test");
        assertPassCreateConnector("All", props);
    }

}