class DummyClass_53218 {
    @Test
    public void testUpdateGroupUpdatesMemberUsers() {
        ScimGroup g1 = createGroup(CFID, JOEL, VIDYA);
        ScimGroup g2 = createGroup(CF_MGR, DALE);
        ScimGroupMember m1 = new ScimGroupMember(g1.getId(), ScimGroupMember.Type.GROUP);
        ScimGroupMember m2 = new ScimGroupMember(g2.getId(), ScimGroupMember.Type.GROUP);
        ScimGroup g3 = createGroup(CF_DEV, m1, m2);

        validateUserGroups(JOEL.getMemberId(), CFID, CF_DEV);
        validateUserGroups(VIDYA.getMemberId(), CFID, CF_DEV);
        validateUserGroups(DALE.getMemberId(), CF_MGR, CF_DEV);

        ScimGroup g4 = updateGroup(g3.getId(), "new_name", m1);

        // check that we did not create a new group, but only updated the
        // existing one
        assertEquals(g3, g4);
        // check that member users were updated
        validateUserGroups(DALE.getMemberId(), CF_MGR);
        validateUserGroups(JOEL.getMemberId(), CFID, "new_name");
        validateUserGroups(VIDYA.getMemberId(), CFID, "new_name");
    }

}