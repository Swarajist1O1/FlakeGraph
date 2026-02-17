class DummyClass_98214 {
    @Test
    public void testBuilder() {
        ConnectionOptions.Builder builder = ConnectionOptions.builder();
        builder.allowSelfSignedCertificates(true);
        builder.disableHostnameVerification(false);
        builder.maxConnections(10);
        builder.connectionTimeoutMs(100);
        builder.requestTimeoutMs(200);
        builder.socketTimeoutMs(300);
        builder.proxyHost("proxyHost");
        builder.proxyPort(1234);
        builder.proxyUsername("proxyUser");
        builder.proxyPassword("proxyPassword");
        builder.proxyProtocol("https:");
        ConnectionOptions options = builder.build();
        Assert.assertEquals(true, options.isAllowSelfSignedCertificates());
        Assert.assertEquals(false, options.isDisableHostnameVerification());
        Assert.assertEquals(10, options.getMaxConnections());
        Assert.assertEquals(100, options.getConnectionTimeoutMs());
        Assert.assertEquals(200, options.getRequestTimeoutMs());
        Assert.assertEquals(300, options.getSocketTimeoutMs());
        Assert.assertEquals("proxyHost", options.getProxyHost());
        Assert.assertEquals(1234, options.getProxyPort());
        Assert.assertEquals("proxyUser", options.getProxyUsername());
        Assert.assertEquals("proxyPassword", options.getProxyPassword());
        Assert.assertEquals("https:", options.getProxyProtocol());
    }

}