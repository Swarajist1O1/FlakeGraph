class DummyClass_84563 {
    @Test
    public void testRemove1() throws Exception {
        String dir = "/testRemove1";
        final int numClients = 1;
        ZooKeeper[] clients = new ZooKeeper[numClients];
        DistributedQueue[] queueHandles = new DistributedQueue[numClients];
        for (int i = 0; i < clients.length; i++) {
            clients[i] = createClient();
            queueHandles[i] = new DistributedQueue(clients[i], dir, null);
        }

        try {
            queueHandles[0].remove();
        } catch (NoSuchElementException e) {
            return;
        }

        fail();
    }

}