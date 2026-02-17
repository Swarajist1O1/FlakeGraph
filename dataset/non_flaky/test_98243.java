class DummyClass_98243 {
    @Test
    public void testGetOrCreateByPathNoRoot() throws RepositoryException {
        String base = testRoot + "/foo";
        Node inter = JcrUtils.getOrCreateByPath(base, "nt:unstructured", superuser);
        assertEquals(base, inter.getPath());
        superuser.save();

        // test what happens if getRootNode() throws
        Session mockedSession = Mockito.spy(superuser);
        Mockito.when(mockedSession.getRootNode()).thenThrow(new AccessDeniedException("access denied"));
        Mockito.when(mockedSession.getNode("/")).thenThrow(new AccessDeniedException("access denied"));
        Mockito.when(mockedSession.getItem("/")).thenThrow(new AccessDeniedException("access denied"));
        Mockito.when(mockedSession.nodeExists("/")).thenReturn(false);

        Node result = JcrUtils.getOrCreateByPath(base + "/bar", false, null, null, mockedSession, false);
        mockedSession.save();
        assertEquals(base + "/bar", result.getPath());

        // already exists -> nop
        Node result2 = JcrUtils.getOrCreateByPath(base + "/bar", false, null, null, mockedSession, false);
        mockedSession.save();
        assertEquals(base + "/bar", result2.getPath());

        // create unique
        Node result3 = JcrUtils.getOrCreateByPath(base + "/bar", true, null, null, mockedSession, false);
        mockedSession.save();
        assertEquals(base + "/bar0", result3.getPath());

        // already exists with createUnique == false should pass even when parent isn't readable
        Mockito.when(mockedSession.getNode(base)).thenThrow(new AccessDeniedException("access denied"));
        Mockito.when(mockedSession.getItem(base)).thenThrow(new AccessDeniedException("access denied"));
        Mockito.when(mockedSession.nodeExists(base)).thenReturn(false);
        Node result4 = JcrUtils.getOrCreateByPath(base + "/bar", false, null, null, mockedSession, false);
        mockedSession.save();
        assertEquals(base + "/bar", result4.getPath());
    }

}