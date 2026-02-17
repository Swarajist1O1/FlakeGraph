class DummyClass_53213 {
    @Test
    public void createExistingGroupFailsCorrectly() {
        ScimGroup g1 = createGroup(CFID);
        @SuppressWarnings("unchecked")
        Map<String, String> g2 = client.postForEntity(serverRunning.getUrl(groupEndpoint), g1, Map.class).getBody();
        assertTrue(g2.containsKey("error"));
        assertEquals("scim_resource_already_exists", g2.get("error"));
    }

}