class DummyClass_133902 {
@Test(groups = "unit", testName = "CrumbIssuerApiMockTest")
    public void testGetSystemInfo() throws Exception {
        MockWebServer server = mockWebServer();

        final String value = "04a1109fc2db171362c966ebe9fc87f0";
        server.enqueue(new MockResponse().setBody("Jenkins-Crumb:" + value).setResponseCode(200));
        JenkinsApi jenkinsApi = api(server.getUrl("/"));
        CrumbIssuerApi api = jenkinsApi.crumbIssuerApi();
        try {
            final Crumb instance = api.crumb();
            assertNotNull(instance);
            assertTrue(instance.value().equals(value));
            assertSentAccept(server, "GET", "/crumbIssuer/api/xml?xpath=concat%28//crumbRequestField,%22%3A%22,//crumb%29", MediaType.TEXT_PLAIN);
        } finally {
            jenkinsApi.close();
            server.shutdown();
        }
    }

}