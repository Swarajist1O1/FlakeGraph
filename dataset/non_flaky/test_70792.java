class DummyClass_70792 {
    @Test
    public void testOtherResources() {
        assertFalse(
            DelegatingClassLoader.serviceLoaderManifestForPlugin("META-INF/services/org.apache.kafka.connect.transforms.Transformation"));
        assertFalse(DelegatingClassLoader.serviceLoaderManifestForPlugin("resource/version.properties"));
    }

}