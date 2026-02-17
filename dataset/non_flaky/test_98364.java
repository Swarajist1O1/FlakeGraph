class DummyClass_98364 {
    @AfterEach
    public void tearDown() {
        super.tearDown();
        Unirest.shutDown(true);
        JankyProxy.shutdown();
    }

}