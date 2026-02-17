class DummyClass_84634 {
    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();

        zk = createClient();
        generatePaths(PERSISTENT_CNT, EPHEMERAL_CNT);
    }

}