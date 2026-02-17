class DummyClass_53152 {
    @Test
    public void can_clear_keys() {
        read_json(oldJson);
        assertEquals(1, config.getKeys().size());
        assertNotNull(config.getActiveKeyId());
        config.setKeys(EMPTY_MAP);
        assertEquals(0, config.getKeys().size());
        assertNull(config.getActiveKeyId());
    }

}