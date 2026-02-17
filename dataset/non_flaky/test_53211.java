class DummyClass_53211 {
    @Test
    public void createGroupWithInvalidMembersFailsCorrectly() {
        ScimGroup g = new ScimGroup(null, CFID, IdentityZoneHolder.get().getId());
        ScimGroupMember m2 = new ScimGroupMember("wrongid");
        g.setMembers(Arrays.asList(VIDYA, m2));

        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> r = client.postForEntity(serverRunning.getUrl(groupEndpoint), g, Map.class);
        @SuppressWarnings("unchecked")
        Map<String, String> g1 = r.getBody();
        assertEquals(HttpStatus.BAD_REQUEST, r.getStatusCode());
        assertTrue(g1.containsKey("error"));
        assertTrue(g1.containsKey("message"));
        assertTrue(g1.get("message").contains("Invalid group member"));

        // check that the group was not created
        @SuppressWarnings("unchecked")
        Map<String, String> g2 = client.getForObject(
            serverRunning.getUrl(groupEndpoint + "?filter=displayName eq \"{name}\""), Map.class, CFID);
        assertTrue(g2.containsKey("totalResults"));
        assertEquals(0, g2.get("totalResults"));
    }

}