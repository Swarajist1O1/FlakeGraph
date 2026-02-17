class DummyClass_70805 {
    @Test
    public void testPluginUrlsWithZips() throws Exception {
        createBasicDirectoryLayout();

        List<Path> expectedUrls = new ArrayList<>();
        expectedUrls.add(Files.createFile(pluginPath.resolve("connectorA/my-sink.zip")));
        expectedUrls.add(Files.createFile(pluginPath.resolve("connectorB/a-source.zip")));
        expectedUrls.add(Files.createFile(pluginPath.resolve("transformC/simple-transform.zip")));
        expectedUrls.add(Files.createFile(
                pluginPath.resolve("transformC/deps/another-transform.zip"))
        );

        assertUrls(expectedUrls, PluginUtils.pluginUrls(pluginPath));
    }

}