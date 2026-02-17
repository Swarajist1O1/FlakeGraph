class DummyClass_84627 {
    @Test
    public void testClientCnxnSocketFragility() throws Exception {
        System.setProperty(ZKClientConfig.ZOOKEEPER_CLIENT_CNXN_SOCKET,
                FragileClientCnxnSocketNIO.class.getName());
        System.setProperty(ZKClientConfig.ZOOKEEPER_REQUEST_TIMEOUT, "1000");
        final int[] clientPorts = new int[SERVER_COUNT];
        StringBuilder sb = new StringBuilder();
        String server;

        for (int i = 0; i < SERVER_COUNT; i++) {
            clientPorts[i] = PortAssignment.unique();
            server = "server." + i + "=127.0.0.1:" + PortAssignment.unique() + ":"
                    + PortAssignment.unique() + ":participant;127.0.0.1:" + clientPorts[i];
            sb.append(server + "\n");
        }
        String currentQuorumCfgSection = sb.toString();
        MainThread[] mt = new MainThread[SERVER_COUNT];

        for (int i = 0; i < SERVER_COUNT; i++) {
            mt[i] = new MainThread(i, clientPorts[i], currentQuorumCfgSection, false);
            mt[i].start();
        }

        // Ensure server started
        for (int i = 0; i < SERVER_COUNT; i++) {
            assertTrue(ClientBase.waitForServerUp("127.0.0.1:" + clientPorts[i], CONNECTION_TIMEOUT),
                    "waiting for server " + i + " being up");
        }
        String path = "/testClientCnxnSocketFragility";
        String data = "balabala";
        ClientWatcher watcher = new ClientWatcher();
        zk = new CustomZooKeeper(getCxnString(clientPorts), SESSION_TIMEOUT, watcher);
        watcher.watchFor(zk);

        // Let's see some successful operations
        zk.create(path, data.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        assertEquals(new String(zk.getData(path, false, new Stat())), data);
        assertTrue(!watcher.isSessionExpired());

        // Let's make a broken operation
        socket.mute();
        boolean catchKeeperException = false;
        try {
            zk.getData(path, false, new Stat());
        } catch (KeeperException e) {
            catchKeeperException = true;
            assertFalse(e instanceof KeeperException.SessionExpiredException);
        }
        socket.unmute();
        assertTrue(catchKeeperException);
        assertTrue(!watcher.isSessionExpired());

        GetDataRetryForeverBackgroundTask retryForeverGetData =
                new GetDataRetryForeverBackgroundTask(zk, path);
        retryForeverGetData.startTask();
        // Let's make a broken network
        socket.mute();

        // Let's attempt to close ZooKeeper
        cnxn.attemptClose();

        // Wait some time to expect continuous reconnecting.
        // We try to make reconnecting hit the unsafe region.
        cnxn.waitUntilHitUnsafeRegion();

        // close zk with timeout 1000 milli seconds
        closeZookeeper(zk);
        TimeUnit.MILLISECONDS.sleep(3000);

        // Since we already close zookeeper, we expect that the zk should not be alive.
        assertTrue(!zk.isAlive());
        assertTrue(!watcher.isSessionExpired());

        retryForeverGetData.syncCloseTask();
        for (int i = 0; i < SERVER_COUNT; i++) {
            mt[i].shutdown();
        }
    }

}