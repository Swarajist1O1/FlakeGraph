class DummyClass_84603 {
    @AfterEach
    public void tearDown() {
        if (provider != null) {
            provider.stop();
        }
        CollectorRegistry.defaultRegistry.clear();
    }

}