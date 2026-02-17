class DummyClass_38641 {
    @Test
    public void getProperty() {
        String NC_PORT = "6667";
        environmentVariables.set("NC_PORT", NC_PORT);
        System.setProperty("propertiesImplementation",
                "org.apache.pulsar.io.flume.node.EnvVarResolverProperties");

        Assert.assertEquals(NC_PORT, provider.getFlumeConfiguration()
                .getConfigurationFor("a1")
                .getSourceContext().get("r1").getParameters().get("port"));
    }

}