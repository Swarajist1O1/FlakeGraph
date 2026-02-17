class DummyClass_177227 {
    @Test
    public void runnableWithExceptionContinuesRun() throws Exception {

        final File file = folder.newFile("temp-file.properties");
        final FileWatcherRegistry fileWatcherRegistry = new FileWatcherRegistry();

        final AtomicInteger val = new AtomicInteger(0);
        final FileWatchRegisterKey key = fileWatcherRegistry.register(file.toPath(), () -> {
            try {
                final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                val.set(Integer.valueOf(bufferedReader.readLine()));
            } catch (IOException e) {
                // do nothing
            }
            throw new RuntimeException();
        });

        PrintWriter printWriter = new PrintWriter(file);
        printWriter.print(1);
        printWriter.close();

        await().untilAsserted(() -> assertThat(val.get()).isEqualTo(1));

        assertThat(fileWatcherRegistry.isRunning()).isTrue();

        printWriter = new PrintWriter(file);
        printWriter.print(2);
        printWriter.close();

        await().untilAsserted(() -> assertThat(val.get()).isEqualTo(2));

        assertThat(fileWatcherRegistry.isRunning()).isTrue();

        fileWatcherRegistry.unregister(key);

        assertThat(fileWatcherRegistry.isRunning()).isFalse();

        fileWatcherRegistry.close();
    }

}