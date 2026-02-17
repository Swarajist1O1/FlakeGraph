class DummyClass_70802 {
    @Test
    public void testEmptyStructurePluginUrls() throws Exception {
        createBasicDirectoryLayout();
        assertEquals(Collections.<Path>emptyList(), PluginUtils.pluginUrls(pluginPath));
    }

}