class DummyClass_98213 {
    @Test
    public void testLegacyMaxConnectionsParameter() {
        Map<String, String> serviceParameters = new HashMap<>();
        serviceParameters.put("org.apache.jackrabbit.spi2davex.MaxConnections", "30");
        ConnectionOptions connectionOptions = ConnectionOptions.builder().maxConnections(30).build();
        Assert.assertEquals(connectionOptions, ConnectionOptions.fromServiceFactoryParameters(serviceParameters));
    }

}