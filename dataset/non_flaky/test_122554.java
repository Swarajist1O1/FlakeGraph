class DummyClass_122554 {
    @Test
    public void testSpawnWithPersistentOutputFile() {

        class TemporaryFile implements AutoCloseable {
            private final Path path;
            private TemporaryFile() {
                String outputFileName = ProcessFactoryImplTest.class.getSimpleName() + "-temporary-test-file.out";
                FileAttribute<Set<PosixFilePermission>> fileAttribute = PosixFilePermissions.asFileAttribute(
                        PosixFilePermissions.fromString("rw-------"));
                path = uncheck(() -> Files.createTempFile(outputFileName, ".out", fileAttribute));
            }
            @Override public void close() { uncheck(() -> Files.deleteIfExists(path)); }
        }

        try (TemporaryFile outputPath = new TemporaryFile()) {

}