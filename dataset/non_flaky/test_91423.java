class DummyClass_91423 {
@TestLogging("org.elasticsearch.cluster.service:TRACE,org.elasticsearch.discovery.zen:TRACE,org.elasticsearch.action.search:TRACE," +
    public Settings nodeSettings(int nodeOrdinal) {
        return Settings.builder().put(super.nodeSettings(nodeOrdinal))
                .put(NetworkModule.HTTP_ENABLED.getKey(), true)
            .put(TestZenDiscovery.USE_MOCK_PINGS.getKey(), false)
                .build();
    }

}