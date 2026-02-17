class DummyClass_53214 {
    @Test
    public void deleteGroupUpdatesUser() {
        ScimGroup g1 = createGroup(DELETE_ME, DALE, VIDYA);
        validateUserGroups(DALE.getMemberId(), DELETE_ME);
        validateUserGroups(VIDYA.getMemberId(), DELETE_ME);

        deleteResource(groupEndpoint, g1.getId());

        // check that the group does not exist anymore
        @SuppressWarnings("unchecked")
        Map<String, Object> g2 = client.getForObject(
            serverRunning.getUrl(groupEndpoint + "?filter=displayName eq \"{name}\""), Map.class, DELETE_ME);
        assertTrue(g2.containsKey("totalResults"));
        assertEquals(0, g2.get("totalResults"));

        // check that group membership is updated
        validateUserGroups(DALE.getMemberId());
        validateUserGroups(VIDYA.getMemberId());
    }

}