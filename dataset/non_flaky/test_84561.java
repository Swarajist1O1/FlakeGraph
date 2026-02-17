class DummyClass_84561 {
    @Test
    public void testOffer2() throws Exception {
        String dir = "/testOffer2";
        String testString = "Hello World";
        final int numClients = 2;
        ZooKeeper[] clients = new ZooKeeper[numClients];
        DistributedQueue[] queueHandles = new DistributedQueue[numClients];
        for (int i = 0; i < clients.length; i++) {
            clients[i] = createClient();
            queueHandles[i] = new DistributedQueue(clients[i], dir, null);
        }

        queueHandles[0].offer(testString.getBytes());

        byte[] dequeuedBytes = queueHandles[1].remove();
        assertEquals(new String(dequeuedBytes), testString);
    }

}