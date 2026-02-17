class DummyClass_98248 {
    @Test
    public void testRemove() throws RepositoryException {
        Session s = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
        Node n = s.getRootNode().addNode(("a"));
        s.save();

        n.remove();
        s.save();
    }

}