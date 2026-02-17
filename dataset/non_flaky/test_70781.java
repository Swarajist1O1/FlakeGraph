class DummyClass_70781 {
    @Test
    public void testCreateWithAllowedOverridesForPrincipalPolicy() throws Exception {
        Map<String, String> props = basicConnectorConfig();
        props.put(ConnectorConfig.CONNECTOR_CLIENT_CONSUMER_OVERRIDES_PREFIX + CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "PLAIN");
        assertPassCreateConnector("Principal", props);
    }

}