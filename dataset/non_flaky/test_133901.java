class DummyClass_133901 {
@Test(groups = "unit", testName = "StatisticsApiMockTest")
    public void testOverallLoad() throws Exception {
        MockWebServer server = mockWebServer();

        server.enqueue(new MockResponse().setBody(payloadFromResource("/overall-load.json")).setResponseCode(200));
        JenkinsApi jenkinsApi = api(server.getUrl("/"));
        StatisticsApi api = jenkinsApi.statisticsApi();
        try {
            OverallLoad load = api.overallLoad();
            assertNotNull(load);
            assertSent(server, "GET", "/overallLoad/api/json");
        } finally {
            jenkinsApi.close();
            server.shutdown();
        }
    }

}