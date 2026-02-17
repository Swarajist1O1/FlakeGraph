class DummyClass_77478 {
    @TestLogging(value="org.opensearch.common.settings.IndexScopedSettings:INFO",
    public void testLogSettingUpdate() throws Exception {
        final IndexMetadata metadata = newIndexMeta("index1",
            Settings.builder().put(IndexSettings.INDEX_REFRESH_INTERVAL_SETTING.getKey(), "20s").build());
        final IndexSettings settings = new IndexSettings(metadata, Settings.EMPTY);

        final MockLogAppender mockLogAppender = new MockLogAppender();
        mockLogAppender.addExpectation(new MockLogAppender.SeenEventExpectation(
            "message",
            "org.opensearch.common.settings.IndexScopedSettings",
            Level.INFO,
            "updating [index.refresh_interval] from [20s] to [10s]") {
            @Override
            public boolean innerMatch(LogEvent event) {
                return event.getMarker().getName().equals(" [index1]");
            }

}