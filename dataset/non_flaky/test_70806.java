class DummyClass_70806 {
    @Test
    public void testPluginUrlsWithClasses() throws Exception {
        Files.createDirectories(pluginPath.resolve("org/apache/kafka/converters"));
        Files.createDirectories(pluginPath.resolve("com/mycompany/transforms"));
        Files.createDirectories(pluginPath.resolve("edu/research/connectors"));
        Files.createFile(pluginPath.resolve("org/apache/kafka/converters/README.txt"));
        Files.createFile(pluginPath.resolve("org/apache/kafka/converters/AlienFormat.class"));
        Files.createDirectories(pluginPath.resolve("com/mycompany/transforms/Blackhole.class"));
        Files.createDirectories(pluginPath.resolve("edu/research/connectors/HalSink.class"));

        List<Path> expectedUrls = new ArrayList<>();
        expectedUrls.add(pluginPath);

        assertUrls(expectedUrls, PluginUtils.pluginUrls(pluginPath));
    }

}