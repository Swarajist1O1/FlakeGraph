class DummyClass_84610 {
    @Test
    public void testZooKeeperWithCustomHostProvider() throws IOException, InterruptedException {
        final int CLIENT_PORT = PortAssignment.unique();
        final HostProvider specialHostProvider = new SpecialHostProvider();
        int expectedCounter = 3;
        counter.set(expectedCounter);

        ZooKeeper zkDefaults = new ZooKeeper(
            "127.0.0.1:" + CLIENT_PORT,
            ClientBase.CONNECTION_TIMEOUT,
            DummyWatcher.INSTANCE,
            false);

        ZooKeeper zkSpecial = new ZooKeeper(
                "127.0.0.1:" + CLIENT_PORT,
                ClientBase.CONNECTION_TIMEOUT,
                DummyWatcher.INSTANCE,
                false,
                specialHostProvider);

        assertTrue(counter.get() == expectedCounter);
        zkDefaults.updateServerList("127.0.0.1:" + PortAssignment.unique());
        assertTrue(counter.get() == expectedCounter);

        zkSpecial.updateServerList("127.0.0.1:" + PortAssignment.unique());
        expectedCounter--;
        assertTrue(counter.get() == expectedCounter);
    }

}