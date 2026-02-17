class DummyClass_162389 {
    @Test
    public void forHostFilePathWithPermission() throws Exception {
        final Path file = createTempFile("somepath");
        final MountableFile mountableFile = MountableFile.forHostPath(file.toString(), TEST_FILE_MODE);
        performChecks(mountableFile);
        assertEquals("Valid file mode.", BASE_FILE_MODE | TEST_FILE_MODE, mountableFile.getFileMode());
    }

}