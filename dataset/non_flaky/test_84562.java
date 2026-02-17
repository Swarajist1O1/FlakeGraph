class DummyClass_84562 {
    @Test
    public void testTake1() throws Exception {
        String dir = "/testTake1";
        String testString = "Hello World";
        final int numClients = 1;
        ZooKeeper[] clients = new ZooKeeper[numClients];
        DistributedQueue[] queueHandles = new DistributedQueue[numClients];
        for (int i = 0; i < clients.length; i++) {
            clients[i] = createClient();
            queueHandles[i] = new DistributedQueue(clients[i], dir, null);
        }

        queueHandles[0].offer(testString.getBytes());

        byte[] dequeuedBytes = queueHandles[0].take();
        assertEquals(new String(dequeuedBytes), testString);
    }

}