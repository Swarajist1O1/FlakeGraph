class DummyClass_53210 {
    @Test
    public void createGroupWithMembersSucceeds() {
        ScimGroup g1 = createGroup(CFID, JOEL, DALE, VIDYA);
        // Check we can GET the group
        ScimGroup g2 = client.getForObject(serverRunning.getUrl(groupEndpoint + "/{id}"), ScimGroup.class, g1.getId());
        assertEquals(g1, g2);
        assertEquals(3, g2.getMembers().size());
        assertTrue(g2.getMembers().contains(JOEL));
        assertTrue(g2.getMembers().contains(DALE));
        assertTrue(g2.getMembers().contains(VIDYA));

        // check that User.groups is updated
        validateUserGroups(JOEL.getMemberId(), CFID);
        validateUserGroups(DALE.getMemberId(), CFID);
        validateUserGroups(VIDYA.getMemberId(), CFID);
    }

}