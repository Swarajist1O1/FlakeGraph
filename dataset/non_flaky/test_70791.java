class DummyClass_70791 {
    @Test
    public void testWhiteListedManifestResources() {
        assertTrue(
            DelegatingClassLoader.serviceLoaderManifestForPlugin("META-INF/services/org.apache.kafka.connect.rest.ConnectRestExtension"));
        assertTrue(
            DelegatingClassLoader.serviceLoaderManifestForPlugin("META-INF/services/org.apache.kafka.common.config.provider.ConfigProvider"));
    }

}