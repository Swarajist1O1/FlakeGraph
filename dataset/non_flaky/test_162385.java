class DummyClass_162385 {
    @Test
    public void forHostPath() throws Exception {
        final Path file = createTempFile("somepath");
        final MountableFile mountableFile = MountableFile.forHostPath(file.toString());

        performChecks(mountableFile);
    }

}