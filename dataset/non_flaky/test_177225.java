class DummyClass_177225 {
    @Test
    public void emptyGroupStopsBackgroundThread() throws Exception {

        final File file = folder.newFile("temp-file.properties");
        final File file2 = folder.newFile("temp-file2.properties");

        final FileWatcherRegistry fileWatcherRegistry =
                new FileWatcherRegistry();
        final FileWatchRegisterKey key1 = fileWatcherRegistry.register(file.toPath(), () -> {});
        final FileWatchRegisterKey key2 = fileWatcherRegistry.register(file2.toPath(), () -> {});

        assertThat(fileWatcherRegistry.isRunning()).isTrue();

        fileWatcherRegistry.unregister(key1);

        assertThat(fileWatcherRegistry.isRunning()).isTrue();

        fileWatcherRegistry.unregister(key2);

        assertThat(fileWatcherRegistry.isRunning()).isFalse();
    }

}