class DummyClass_91493 {
@TestLogging("org.elasticsearch.index.reindex:DEBUG,org.elasticsearch.action.bulk:DEBUG")
    public void clearAllowedOperations() {
        ALLOWED_OPERATIONS.drainPermits();
    }

}