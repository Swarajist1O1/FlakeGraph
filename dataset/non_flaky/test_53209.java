class DummyClass_53209 {
    @Test
    public void createGroupSucceeds() throws Exception {
        ScimGroup g1 = createGroup(CFID);
        // Check we can GET the group
        ScimGroup g2 = client.getForObject(serverRunning.getUrl(groupEndpoint + "/{id}"), ScimGroup.class, g1.getId());
        assertEquals(g1, g2);
    }

}