class DummyClass_84560 {
    @Test
    public void testOffer1() throws Exception {
        String dir = "/testOffer1";
        String testString = "Hello World";
        final int numClients = 1;
        ZooKeeper[] clients = new ZooKeeper[numClients];
        DistributedQueue[] queueHandles = new DistributedQueue[numClients];
        for (int i = 0; i < clients.length; i++) {
            clients[i] = createClient();
            queueHandles[i] = new DistributedQueue(clients[i], dir, null);
        }

        queueHandles[0].offer(testString.getBytes());

        byte[] dequeuedBytes = queueHandles[0].remove();
        assertEquals(new String(dequeuedBytes), testString);
    }

}