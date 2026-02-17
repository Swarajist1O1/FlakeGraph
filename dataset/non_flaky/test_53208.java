class DummyClass_53208 {
    @Test
    public void getGroupsWithoutAttributesReturnsAllData() {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = client.getForEntity(serverRunning.getUrl(groupEndpoint), Map.class);

        @SuppressWarnings("rawtypes")
        Map results = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue("There should be more than zero users", (Integer) results.get("totalResults") > 0);
        assertTrue("There should be some resources", ((Collection<?>) results.get("resources")).size() > 0);
        @SuppressWarnings("rawtypes")
        Map firstGroup = (Map) ((List) results.get("resources")).get(0);
        assertTrue(firstGroup.containsKey("id"));
        assertTrue(firstGroup.containsKey("displayName"));
        assertTrue(firstGroup.containsKey("schemas"));
        assertTrue(firstGroup.containsKey("meta"));
    }

}