class DummyClass_162388 {
    @Test
    public void forClasspathResourceWithPermission() throws Exception {
        final MountableFile mountableFile = MountableFile.forClasspathResource("mappable-resource/test-resource.txt",
                TEST_FILE_MODE);

        performChecks(mountableFile);
        assertEquals("Valid file mode.", BASE_FILE_MODE | TEST_FILE_MODE, mountableFile.getFileMode());
    }

}