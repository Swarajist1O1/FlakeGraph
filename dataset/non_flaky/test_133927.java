class DummyClass_133927 {
@Test(groups = "unit", testName = "QueueApiMockTest")
    public void testGetQueue() throws Exception {
        MockWebServer server = mockWebServer();
        String body = payloadFromResource("/queue.json");
        server.enqueue(new MockResponse().setBody(body).setResponseCode(200));
        JenkinsApi jenkinsApi = api(server.getUrl("/"));
        QueueApi api = jenkinsApi.queueApi();
        try {
            List<QueueItem> output = api.queue();
            assertTrue(output.size() == 2);
            assertSent(server, "GET", "/queue/api/json");
        } finally {
            jenkinsApi.close();
            server.shutdown();
        }
    }

}