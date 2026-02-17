class DummyClass_84647 {
    @Test
    public void testDeleteRecursiveFail() throws IOException, InterruptedException, KeeperException {
        final ZooKeeper zk = createClient();
        setupDataTree(zk);

        ACL deleteProtection = new ACL(ZooDefs.Perms.DELETE, new Id("digest", "user:tl+z3z0vO6PfPfEENfLF96E6pM0="/* password is test */));
        List<ACL> acls = Arrays.asList(new ACL(ZooDefs.Perms.READ, Ids.ANYONE_ID_UNSAFE), deleteProtection);

        // poison the well
        zk.create("/a/c/0/surprise", "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        assertEquals(1, zk.getACL("/a/c/0", new Stat()).size());
        zk.setACL("/a/c/0", acls, -1);
        assertEquals(2, zk.getACL("/a/c/0", new Stat()).size());

        assertFalse(ZKUtil.deleteRecursive(zk, "/a/c", 1000));
        List<String> children = zk.getChildren("/a", false);
        assertEquals(2, children.size(), "2 children - c should fail to be deleted ");
        assertTrue(children.contains("b"));

        assertTrue(ZKUtil.deleteRecursive(zk, "/a/b", 1000));
        children = zk.getChildren("/a", false);
        assertEquals(1, children.size(), "1 children - b should be deleted ");

        // acquire immunity to poison
        zk.addAuthInfo(deleteProtection.getId().getScheme(), "user:test".getBytes());

        assertTrue(ZKUtil.deleteRecursive(zk, "/a", 1000));
        assertNull(zk.exists("/a", null));
    }

}