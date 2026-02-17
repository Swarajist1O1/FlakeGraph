class DummyClass_53216 {
    @Test
    public void deleteMemberGroupUpdatesGroup() {
        ScimGroup g1 = createGroup(CFID, VIDYA);
        ScimGroupMember m2 = new ScimGroupMember(g1.getId(), ScimGroupMember.Type.GROUP);
        ScimGroup g2 = createGroup(CF_DEV, DALE, m2);
        assertTrue(g2.getMembers().contains(m2));
        validateUserGroups(VIDYA.getMemberId(), CFID, CF_DEV);

        deleteResource(groupEndpoint, g1.getId());

        // check that parent group is updated
        ScimGroup g3 = client.getForObject(serverRunning.getUrl(groupEndpoint + "/{id}"), ScimGroup.class, g2.getId());
        assertEquals(1, g3.getMembers().size());
        assertFalse(g3.getMembers().contains(m2));
    }

}