class DummyClass_53215 {
    @Test
    public void deleteNonExistentGroupFailsCorrectly() {
        @SuppressWarnings("unchecked")
        Map<String, Object> g = deleteResource(groupEndpoint, DELETE_ME).getBody();
        assertTrue(g.containsKey("error"));
        assertEquals("scim_resource_not_found", g.get("error"));
    }

}