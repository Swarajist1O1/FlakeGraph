class DummyClass_53212 {
    @Test
    public void createGroupWithMemberGroupSucceeds() {
        ScimGroup g1 = createGroup(CFID, VIDYA);
        ScimGroupMember m2 = new ScimGroupMember(g1.getId(), ScimGroupMember.Type.GROUP);
        ScimGroup g2 = createGroup(CF_DEV, m2);

        // Check we can GET the group
        ScimGroup g3 = client.getForObject(serverRunning.getUrl(groupEndpoint + "/{id}"), ScimGroup.class, g2.getId());
        assertEquals(g2, g3);
        assertEquals(1, g3.getMembers().size());
        assertTrue(g3.getMembers().contains(m2));

        // check that User.groups is updated
        validateUserGroups(VIDYA.getMemberId(), CFID, CF_DEV);
    }

}