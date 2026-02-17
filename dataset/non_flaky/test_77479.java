class DummyClass_77479 {
    @TestLogging(
    public void testTracerLog() throws Exception {
        final String includeSettings;
        final String excludeSettings;
        if (randomBoolean()) {
            includeSettings = randomBoolean() ? "*" : "";
        } else {
            includeSettings = "/internal/test";
        }
        excludeSettings = "/internal/testNotSeen";

        final ClusterSettings clusterSettings = new ClusterSettings(Settings.EMPTY, ClusterSettings.BUILT_IN_CLUSTER_SETTINGS);
        try (AbstractHttpServerTransport transport =
                 new AbstractHttpServerTransport(Settings.EMPTY, networkService, bigArrays, threadPool, xContentRegistry(),
                     new HttpServerTransport.Dispatcher() {
                         @Override
                         public void dispatchRequest(RestRequest request, RestChannel channel, ThreadContext threadContext) {
                             channel.sendResponse(emptyResponse(RestStatus.OK));
                         }

}