class DummyClass_84624 {
    @BeforeEach
    public void setUp() {
        ClientCnxnSocketNetty.setTestAllocator(TestByteBufAllocator.getInstance());
    }

}