class DummyClass_133912 {
@Test(groups = "unit", testName = "PluginManagerApiMockTest")
    public void testGetPlugins() throws Exception {
        final MockWebServer server = mockWebServer();
        server.enqueue(new MockResponse().setBody(payloadFromResource("/plugins.json")).setResponseCode(200));
        
        final JenkinsApi jenkinsApi = api(server.getUrl("/"));
        final PluginManagerApi api = jenkinsApi.pluginManagerApi();
        try {
            final Plugins plugins = api.plugins(3, null);
            assertNotNull(plugins);
            assertTrue(plugins.errors().isEmpty());
            assertFalse(plugins.plugins().isEmpty());
            assertNotNull(plugins.plugins().get(0).shortName());
            final Map<String, Object> queryParams = Maps.newHashMap();
            queryParams.put("depth", 3);
            assertSent(server, "GET", "/pluginManager/api/json", queryParams);
        } finally {
            jenkinsApi.close();
            server.shutdown();
        }
    }

}