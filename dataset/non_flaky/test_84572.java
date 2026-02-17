class DummyClass_84572 {
    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();

        zooKeeper = createClient();

        zooKeeper.create(
            TEST_ROOT_NODE + Thread.currentThread().getId(),
            new byte[0],
            ZooDefs.Ids.OPEN_ACL_UNSAFE,
            CreateMode.PERSISTENT);
    }

}