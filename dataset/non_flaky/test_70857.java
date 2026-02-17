class DummyClass_70857 {
    @Test
    public void testEmbeddedConfigRegexRouter() {
        // Validate that we can construct a Connector config containing the extended config for the transform
        HashMap<String, String> connProps = new HashMap<>();
        connProps.put("name", "foo");
        connProps.put("connector.class", MockConnector.class.getName());
        connProps.put("transforms", "example");
        connProps.put("transforms.example.type", RegexRouter.class.getName());
        connProps.put("transforms.example.regex", "(.*)");
        connProps.put("transforms.example.replacement", "prefix-$1");

        Plugins plugins = null; // Safe when we're only constructing the config
        new ConnectorConfig(plugins, connProps);
    }

}