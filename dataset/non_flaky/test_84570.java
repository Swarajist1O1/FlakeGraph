class DummyClass_84570 {
    @Test
    public void testTakeWait1() throws Exception {
        String dir = "/testTakeWait1";
        final String testString = "Hello World";
        final int numClients = 1;
        final ZooKeeper[] clients = new ZooKeeper[numClients];
        final DistributedQueue[] queueHandles = new DistributedQueue[numClients];
        for (int i = 0; i < clients.length; i++) {
            clients[i] = createClient();
            queueHandles[i] = new DistributedQueue(clients[i], dir, null);
        }

        final byte[][] takeResult = new byte[1][];
        Thread takeThread = new Thread(() -> {
            try {
                takeResult[0] = queueHandles[0].take();
            } catch (KeeperException | InterruptedException ignore) {
                // no op
            }
        });
        takeThread.start();

        Thread.sleep(1000);
        Thread offerThread = new Thread(() -> {
            try {
                queueHandles[0].offer(testString.getBytes());
            } catch (KeeperException | InterruptedException ignore) {
                // no op
            }
        });
        offerThread.start();
        offerThread.join();

        takeThread.join();

        assertNotNull(takeResult[0]);
        assertEquals(new String(takeResult[0]), testString);
    }

}