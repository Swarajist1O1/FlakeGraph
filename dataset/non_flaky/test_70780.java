class DummyClass_70780 {
    @Test
    public void testCreateWithNotAllowedOverridesForPrincipalPolicy() throws Exception {
        Map<String, String> props = basicConnectorConfig();
        props.put(ConnectorConfig.CONNECTOR_CLIENT_CONSUMER_OVERRIDES_PREFIX + SaslConfigs.SASL_JAAS_CONFIG, "sasl");
        props.put(ConnectorConfig.CONNECTOR_CLIENT_CONSUMER_OVERRIDES_PREFIX + ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        assertFailCreateConnector("Principal", props);
    }

}