class DummyClass_84646 {
    @Test
    public void testDeleteRecursive() throws IOException, InterruptedException, KeeperException {
        final ZooKeeper zk = createClient();
        setupDataTree(zk);

        assertTrue(ZKUtil.deleteRecursive(zk, "/a/c", 1000));
        List<String> children = zk.getChildren("/a", false);
        assertEquals(1, children.size(), "1 children - c should be deleted ");
        assertTrue(children.contains("b"));

        assertTrue(ZKUtil.deleteRecursive(zk, "/a", 1000));
        assertNull(zk.exists("/a", null));
    }

}