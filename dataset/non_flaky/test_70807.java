class DummyClass_70807 {
    @Test
    public void testPluginUrlsWithAbsoluteSymlink() throws Exception {
        createBasicDirectoryLayout();

        Path anotherPath = rootDir.newFolder("moreplugins").toPath().toRealPath();
        Files.createDirectories(anotherPath.resolve("connectorB-deps"));
        Files.createSymbolicLink(
                pluginPath.resolve("connectorB/deps/symlink"),
                anotherPath.resolve("connectorB-deps")
        );

        List<Path> expectedUrls = createBasicExpectedUrls();
        expectedUrls.add(Files.createFile(anotherPath.resolve("connectorB-deps/converter.jar")));

        assertUrls(expectedUrls, PluginUtils.pluginUrls(pluginPath));
    }

}