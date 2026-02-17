class DummyClass_70809 {
    @Test
    public void testPluginUrlsWithRelativeSymlinkForwards() throws Exception {
        // Since this test case defines a relative symlink within an already included path, the main
        // assertion of this test is absence of exceptions and correct resolution of paths.
        createBasicDirectoryLayout();
        Files.createDirectories(pluginPath.resolve("connectorB/deps/more"));
        Files.createSymbolicLink(
                pluginPath.resolve("connectorB/deps/symlink"),
                Paths.get("more")
        );

        List<Path> expectedUrls = createBasicExpectedUrls();
        expectedUrls.add(
                Files.createFile(pluginPath.resolve("connectorB/deps/more/converter.jar"))
        );

        assertUrls(expectedUrls, PluginUtils.pluginUrls(pluginPath));
    }

}