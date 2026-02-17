class DummyClass_177228 {
    @Test
    public void testMultipleFileSystems() throws Exception {

        final FileWatcherRegistry fileWatcherRegistry = new FileWatcherRegistry();

        final Path path1 = createMockedPath();
        final Path path2 = createMockedPath();

        final FileWatchRegisterKey key1 = fileWatcherRegistry.register(path1, () -> {});
        final FileWatchRegisterKey key2 = fileWatcherRegistry.register(path2, () -> {});
        assertThat(fileWatcherRegistry.isRunning()).isTrue();

        fileWatcherRegistry.unregister(key1);
        assertThat(fileWatcherRegistry.isRunning()).isTrue();

        fileWatcherRegistry.unregister(key2);
        assertThat(fileWatcherRegistry.isRunning()).isFalse();
    }

}