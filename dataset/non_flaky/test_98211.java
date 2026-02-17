class DummyClass_98211 {
    @Test
    public void testWithSystemPropertiesAndIncompatibleConnectionOptions() throws RepositoryException {
        ConnectionOptions connectionOptions = ConnectionOptions.builder().useSystemProperties(true).allowSelfSignedCertificates(true).build();
        assertThrows(RepositoryException.class, ()->getRepositoryService("https://jackrabbit.apache.org/jcr", connectionOptions));
        ConnectionOptions connectionOptions2 = ConnectionOptions.builder().useSystemProperties(true).disableHostnameVerification(true).build();
        assertThrows(RepositoryException.class, ()->getRepositoryService("https://jackrabbit.apache.org/jcr", connectionOptions2));
    }

}