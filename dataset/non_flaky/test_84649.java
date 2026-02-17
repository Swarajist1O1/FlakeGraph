class DummyClass_84649 {
    @Test
    public void testDeleteRecursiveAsync() throws IOException, InterruptedException, KeeperException {
        final ZooKeeper zk = createClient();
        // making sure setdata works on /
        zk.setData("/", "some".getBytes(), -1);
        zk.create("/a", "some".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zk.create("/a/b", "some".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zk.create("/a/b/v", "some".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zk.create("/a/b/v/1", "some".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zk.create("/a/c", "some".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zk.create("/a/c/v", "some".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        for (int i = 0; i < 50; ++i) {
            zk.create("/a/c/" + i, "some".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        List<String> children = zk.getChildren("/a", false);

        assertEquals(children.size(), 2, "2 children - b & c should be present ");
        assertTrue(children.contains("b"));
        assertTrue(children.contains("c"));

        VoidCallback cb = new VoidCallback() {

            @Override
            public void processResult(int rc, String path, Object ctx) {
                synchronized (ctx) {
                    ((AtomicInteger) ctx).set(4);
                    ctx.notify();
                }
            }

}