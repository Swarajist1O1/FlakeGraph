class DummyClass_53163 {
    @Test
    public void testSerializeWithDescription() {
        group.setDescription("description");
        String json = JsonUtils.writeValueAsString(group);
        group = JsonUtils.readValue(json, ScimGroup.class);
        assertEquals("id", group.getId());
        assertEquals("name", group.getDisplayName());
        assertEquals("zoneId", group.getZoneId());
        assertEquals("description", group.getDescription());
    }

}