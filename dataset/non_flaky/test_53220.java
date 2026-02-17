class DummyClass_53220 {
    @Test
    public void testAccessTokenReflectsGroupMembershipForPasswordGrant() throws Exception {

        createTestClient(DELETE_ME, "secret", CFID);
        ScimUser user = createUser(DELETE_ME, "Passwo3d");
        createGroup(CFID, new ScimGroupMember(user.getId()));
        OAuth2AccessToken token = getAccessTokenWithPassword(DELETE_ME, "secret", DELETE_ME, "Passwo3d");
        assertTrue("Wrong token: " + token, token.getScope().contains(CFID));

        deleteTestClient(DELETE_ME);
        deleteResource(userEndpoint, user.getId());

    }

}