class DummyClass_177226 {
    @Test
    public void closeEndpointGroupStopsRegistry() throws Exception {

        final File file = folder.newFile("temp-file.properties");

        final FileWatcherRegistry fileWatcherRegistry = new FileWatcherRegistry();
        fileWatcherRegistry.register(file.toPath(), () -> {});

        assertThat(fileWatcherRegistry.isRunning()).isTrue();

        fileWatcherRegistry.close();

        assertThat(fileWatcherRegistry.isRunning()).isFalse();
    }

}