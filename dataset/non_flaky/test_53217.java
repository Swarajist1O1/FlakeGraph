class DummyClass_53217 {
    @Test
    public void testDeleteMemberUserUpdatesGroups() {
        ScimGroupMember toDelete = new ScimGroupMember(createUser(DELETE_ME, "Passwo3d").getId());
        ScimGroup g1 = createGroup(CFID, JOEL, DALE, toDelete);
        ScimGroup g2 = createGroup(CF_MGR, DALE, toDelete);
        deleteResource(userEndpoint, toDelete.getMemberId());

        // check that membership has been updated
        ScimGroup g3 = client.getForObject(serverRunning.getUrl(groupEndpoint + "/{id}"), ScimGroup.class, g1.getId());
        assertEquals(2, g3.getMembers().size());
        assertFalse(g3.getMembers().contains(toDelete));

        g3 = client.getForObject(serverRunning.getUrl(groupEndpoint + "/{id}"), ScimGroup.class, g2.getId());
        assertEquals(1, g3.getMembers().size());
        assertFalse(g3.getMembers().contains(toDelete));
    }

}