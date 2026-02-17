class DummyClass_98209 {
    @Test
    public void testGetAgainstTrustedCertServerWithSystemProperties() throws RepositoryException, ClientProtocolException, IOException {
        assumeTrue("Cannot connect to http://www.apache.org", canConnectTo("http://www.apache.org"));
        // use dedicated trust store
        Path keyStorePath = tmpDirectory.getRoot().toPath().resolve("emptyPKCS12.keystore");
        try (InputStream is = this.getClass().getResourceAsStream("emptyPKCS12.keystore")) {
            Files.copy(is, keyStorePath);
        }
        String oldTrustStore = System.setProperty("javax.net.ssl.trustStore", keyStorePath.toString());
        String oldTrustStorePassword = System.setProperty("javax.net.ssl.trustStorePassword", "storePassword");
        String oldDebug = System.setProperty("javax.net.debug", "ssl");
        try {
            ConnectionOptions connectionOptions = ConnectionOptions.builder().useSystemProperties(true).build();
            RepositoryServiceImpl repositoryServiceImpl = RepositoryServiceImplTest.getRepositoryService("https://jackrabbit.apache.org/jcr", connectionOptions);
            HttpClient client = repositoryServiceImpl.getClient(null);
            HttpGet get = new HttpGet("https://jackrabbit.apache.org/jcr/index.html");
            // connection must fail as cert is not trusted due to used trust store being empty
            assertThrows(SSLException.class, () -> client.execute(get, new BasicResponseHandler()));
        } finally {
            setOrClearSystemProperty("javax.net.ssl.trustStore", oldTrustStore);
            setOrClearSystemProperty("javax.net.ssl.trustStorePassword", oldTrustStorePassword);
            setOrClearSystemProperty("javax.net.debug", oldDebug);
        }
    }

}