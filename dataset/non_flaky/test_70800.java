class DummyClass_70800 {
    @Test
    public void testConnectorClientConfigOverridePolicy() {
        assertFalse(PluginUtils.shouldLoadInIsolation(
                "org.apache.kafka.connect.connector.policy.ConnectorClientConfigOverridePolicy")
        );
        assertTrue(PluginUtils.shouldLoadInIsolation(
                "org.apache.kafka.connect.connector.policy.AbstractConnectorClientConfigOverridePolicy")
        );
        assertTrue(PluginUtils.shouldLoadInIsolation(
                "org.apache.kafka.connect.connector.policy.AllConnectorClientConfigOverridePolicy")
        );
        assertTrue(PluginUtils.shouldLoadInIsolation(
                "org.apache.kafka.connect.connector.policy.NoneConnectorClientConfigOverridePolicy")
        );
        assertTrue(PluginUtils.shouldLoadInIsolation(
                "org.apache.kafka.connect.connector.policy.PrincipalConnectorClientConfigOverridePolicy")
        );
    }

}