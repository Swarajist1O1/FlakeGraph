class DummyClass_53146 {
    @Test
    public void map_is_not_null_by_default() {
        Map<String, SamlKey> keys = config.getKeys();
        assertNotNull(keys);
        assertEquals(0, keys.size());
        assertNull(config.getActiveKeyId());
    }

}