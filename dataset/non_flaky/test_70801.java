class DummyClass_70801 {
    @Test
    public void testEmptyPluginUrls() throws Exception {
        assertEquals(Collections.<Path>emptyList(), PluginUtils.pluginUrls(pluginPath));
    }

}