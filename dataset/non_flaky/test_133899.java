class DummyClass_133899 {
    @Test
    public void testGetPlugins() {
        final Plugins plugins = api().plugins(3, null);
        assertNotNull(plugins);
        assertTrue(plugins.errors().isEmpty());
        assertFalse(plugins.plugins().isEmpty());
        assertNotNull(plugins.plugins().get(0).shortName());
    }

}