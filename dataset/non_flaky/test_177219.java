class DummyClass_177219 {
    @Test
    public void testPropertyFileWatcherRunnableExitsOnInterrupt() throws InterruptedException {
        final WatchService watchService = mock(WatchService.class);
        final FileWatcherRunnable fileWatcherRunnable = new FileWatcherRunnable(watchService, mock(
                FileSystemWatchContext.class));
        when(watchService.take()).then(invocation -> {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.yield();
            }
            return null;
        });
        final Thread thread = new Thread(fileWatcherRunnable);
        thread.start();
        thread.interrupt();
        await().untilAsserted(() -> assertThat(thread.isAlive()).isFalse());
    }

}