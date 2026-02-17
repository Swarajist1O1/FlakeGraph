class DummyClass_70779 {
    @Test
    public void testCreateWithOverridesForNonePolicy() throws Exception {
        Map<String, String> props = basicConnectorConfig();
        props.put(ConnectorConfig.CONNECTOR_CLIENT_CONSUMER_OVERRIDES_PREFIX + SaslConfigs.SASL_JAAS_CONFIG, "sasl");
        assertFailCreateConnector("None", props);
    }

}