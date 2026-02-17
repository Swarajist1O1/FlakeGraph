class DummyClass_84611 {
    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();

        zk = createClient();
        expected = generatePaths(PERSISTENT_CNT, EPHEMERAL_CNT);
    }

}