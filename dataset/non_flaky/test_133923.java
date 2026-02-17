class DummyClass_133923 {
@Test(groups = "unit", testName = "SystemApiMockTest")
    public void testGetSystemInfo() throws Exception {
        MockWebServer server = mockWebServer();

        server.enqueue(
            new MockResponse().setHeader("X-Hudson", "1.395").setHeader("X-Jenkins", JenkinsApiMetadata.BUILD_VERSION)
                .setHeader("X-Jenkins-Session", "cc323b8d").setHeader("X-Hudson-CLI-Port", "50000")
                .setHeader("X-Jenkins-CLI-Port", "50000").setHeader("X-Jenkins-CLI2-Port", "50000")
                .setHeader("X-Instance-Identity", "fdsa").setHeader("X-SSH-Endpoint", "127.0.1.1:46126")
                .setHeader("Server", "Jetty(winstone-2.9)").setResponseCode(200));
        JenkinsApi jenkinsApi = api(server.getUrl("/"));
        SystemApi api = jenkinsApi.systemApi();
        try {
            final SystemInfo version = api.systemInfo();
            assertNotNull(version);
            assertTrue(version.jenkinsVersion().equalsIgnoreCase(JenkinsApiMetadata.BUILD_VERSION));
            assertSent(server, "HEAD", "/");
        } finally {
            jenkinsApi.close();
            server.shutdown();
        }
    }

}