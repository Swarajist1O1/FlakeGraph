class DummyClass_70856 {
    @Test
    public void testEmbeddedConfigMaskField() {
        // Validate that we can construct a Connector config containing the extended config for the transform
        HashMap<String, String> connProps = new HashMap<>();
        connProps.put("name", "foo");
        connProps.put("connector.class", MockConnector.class.getName());
        connProps.put("transforms", "example");
        connProps.put("transforms.example.type", MaskField.Value.class.getName());
        connProps.put("transforms.example.fields", "field");


        Plugins plugins = null; // Safe when we're only constructing the config
        new ConnectorConfig(plugins, connProps);
    }

}