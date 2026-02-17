class DummyClass_84625 {
    @AfterEach
    public void tearDown() {
        ClientCnxnSocketNetty.clearTestAllocator();
        TestByteBufAllocator.checkForLeaks();
    }

}