class DummyClass_84573 {
    @AfterEach
    public void tearDown() throws Exception {
        if (zooKeeper != null) {
            zooKeeper.delete(TEST_ROOT_NODE + Thread.currentThread().getId(), -1);
        }

        super.tearDown();
    }

}