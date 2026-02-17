class DummyClass_70808 {
    @Test
    public void testPluginUrlsWithRelativeSymlinkBackwards() throws Exception {
        createBasicDirectoryLayout();

        Path anotherPath = rootDir.newFolder("moreplugins").toPath().toRealPath();
        Files.createDirectories(anotherPath.resolve("connectorB-deps"));
        Files.createSymbolicLink(
                pluginPath.resolve("connectorB/deps/symlink"),
                Paths.get("../../../moreplugins/connectorB-deps")
        );

        List<Path> expectedUrls = createBasicExpectedUrls();
        expectedUrls.add(Files.createFile(anotherPath.resolve("connectorB-deps/converter.jar")));

        assertUrls(expectedUrls, PluginUtils.pluginUrls(pluginPath));
    }

}