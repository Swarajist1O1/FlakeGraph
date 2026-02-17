class DummyClass_98242 {
    @Test
    public void testGetOrCreateByPath1() throws RepositoryException {
        String path = testRoot + "/foo";
        Node node = JcrUtils.getOrCreateByPath(path, "nt:unstructured", superuser);
        superuser.save();
        assertEquals(path, node.getPath());
        assertTrue(superuser.nodeExists(path));

        // existing top-level node, two new descendant nodes
        String path2 = testRoot + "/foo/a/b";
        Node node2 = JcrUtils.getOrCreateByPath(path2, "nt:unstructured", superuser);
        superuser.save();
        assertEquals(path2, node2.getPath());
        assertTrue(superuser.nodeExists(path2));
    }

}