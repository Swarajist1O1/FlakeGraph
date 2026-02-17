class DummyClass_162382 {
    @Test
    public void forClasspathResourceWithAbsolutePath() throws Exception {
        final MountableFile mountableFile = MountableFile.forClasspathResource("/mappable-resource/test-resource.txt");

        performChecks(mountableFile);
    }

}