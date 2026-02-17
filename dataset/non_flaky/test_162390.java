class DummyClass_162390 {
    @Test
    public void forHostDirPathWithPermission() throws Exception {
        final Path dir = createTempDir();
        final MountableFile mountableFile = MountableFile.forHostPath(dir.toString(), TEST_FILE_MODE);
        performChecks(mountableFile);
        assertEquals("Valid dir mode.", BASE_DIR_MODE | TEST_FILE_MODE, mountableFile.getFileMode());
    }

}