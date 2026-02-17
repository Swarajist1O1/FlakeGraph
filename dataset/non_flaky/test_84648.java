class DummyClass_84648 {
    @Test
    public void testDeleteRecursiveCli() throws IOException, InterruptedException, CliException, KeeperException {
        final ZooKeeper zk = createClient();
        // making sure setdata works on /
        zk.setData("/", "some".getBytes(), -1);
        zk.create("/a", "some".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zk.create("/a/b", "some".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zk.create("/a/b/v", "some".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zk.create("/a/b/v/1", "some".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zk.create("/a/c", "some".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zk.create("/a/c/v", "some".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        List<String> children = zk.getChildren("/a", false);

        assertEquals(children.size(), 2, "2 children - b & c should be present ");
        assertTrue(children.contains("b"));
        assertTrue(children.contains("c"));

        ZooKeeperMain zkMain = new ZooKeeperMain(zk);
        String cmdstring1 = "deleteall /a";
        zkMain.cl.parseCommand(cmdstring1);
        assertFalse(zkMain.processZKCmd(zkMain.cl));
        assertNull(zk.exists("/a", null));
    }

}