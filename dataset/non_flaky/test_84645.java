class DummyClass_84645 {
    @Test
    public void testEnforceAuthenticationWithMultipleAuthSchemes() throws Exception {
        Map<String, String> prop = new HashMap<>();
        prop.put(removeZooKeeper(AuthenticationHelper.ENFORCE_AUTH_ENABLED), "true");
        prop.put(removeZooKeeper(AuthenticationHelper.ENFORCE_AUTH_SCHEMES), "digest,ip");
        startServer(prop);
        ZKClientConfig config = new ZKClientConfig();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper client =
            new ZooKeeper("127.0.0.1:" + clientPort, CONNECTION_TIMEOUT, getWatcher(countDownLatch),
                config);
        countDownLatch.await();
        // try operation without adding auth info, it should be success as ip auth info is
        // added automatically by server
        String path = "/newAuth" + System.currentTimeMillis();
        String data = "someData";
        client.create(path, data.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        byte[] data1 = client.getData(path, false, null);
        assertEquals(data, new String(data1));
        client.close();
    }

}