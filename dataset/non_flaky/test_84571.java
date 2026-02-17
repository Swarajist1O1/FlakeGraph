class DummyClass_84571 {
    @Test
    public void testTakeWait2() throws Exception {
        String dir = "/testTakeWait2";
        final String testString = "Hello World";
        final int numClients = 1;
        final ZooKeeper[] clients = new ZooKeeper[numClients];
        final DistributedQueue[] queueHandles = new DistributedQueue[numClients];
        for (int i = 0; i < clients.length; i++) {
            clients[i] = createClient();
            queueHandles[i] = new DistributedQueue(clients[i], dir, null);
        }
        int numAttempts = 2;
        for (int i = 0; i < numAttempts; i++) {
            final byte[][] takeResult = new byte[1][];
            final String threadTestString = testString + i;
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
                    queueHandles[0].offer(threadTestString.getBytes());
                } catch (KeeperException | InterruptedException ignore) {
                    // no op
                }
            });
            offerThread.start();
            offerThread.join();

            takeThread.join();

            assertNotNull(takeResult[0]);
            assertEquals(new String(takeResult[0]), threadTestString);
        }
    }

}