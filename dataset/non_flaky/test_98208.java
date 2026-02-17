class DummyClass_98208 {
    @Test
    public void testGetAgainstTrustedCertServer() throws RepositoryException, ClientProtocolException, IOException {
        assumeTrue("Cannot connect to http://www.apache.org", canConnectTo("http://www.apache.org"));
        RepositoryServiceImpl repositoryServiceImpl = RepositoryServiceImplTest.getRepositoryService("https://jackrabbit.apache.org/jcr", ConnectionOptions.builder().build());
        HttpClient client = repositoryServiceImpl.getClient(null);
        HttpGet get = new HttpGet("https://jackrabbit.apache.org/jcr/index.html");
        String content = client.execute(get, new BasicResponseHandler());
        assertFalse(content.isEmpty());
    }

}