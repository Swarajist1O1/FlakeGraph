class DummyClass_53162 {
    @Test
    public void testDeSerializeWithoutDescription() {
        group = JsonUtils.readValue(GROUP_BEFORE_DESCRIPTION, ScimGroup.class);
        assertEquals("id", group.getId());
        assertEquals("name", group.getDisplayName());
        assertEquals("zoneId", group.getZoneId());
        assertNull(group.getDescription());
    }

}