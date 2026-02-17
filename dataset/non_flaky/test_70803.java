class DummyClass_70803 {
    @Test
    public void testPluginUrlsWithJars() throws Exception {
        createBasicDirectoryLayout();

        List<Path> expectedUrls = createBasicExpectedUrls();

        assertUrls(expectedUrls, PluginUtils.pluginUrls(pluginPath));
    }

}