class DummyClass_133926 {
@Test(groups = "unit", testName = "ConfigurationAsCodeApiMockTest")
    public void testCascCheck() throws Exception {
        MockWebServer server = mockWebServer();

        server.enqueue(new MockResponse().setResponseCode(200));
        JenkinsApi jenkinsApi = api(server.url("/").url());
        ConfigurationAsCodeApi api = jenkinsApi.configurationAsCodeApi();
        try {
            RequestStatus requestStatus = api.check("random");
            assertNotNull(requestStatus);
            assertTrue(requestStatus.value());
            assertEquals(requestStatus.errors().size(), 0);
        } finally {
            jenkinsApi.close();
            server.shutdown();
        }
    }

}