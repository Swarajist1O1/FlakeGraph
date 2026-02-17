class DummyClass_162384 {
    @Test
    public void forClasspathResourceFromJarWithAbsolutePath() throws Exception {
        final MountableFile mountableFile = MountableFile.forClasspathResource("/META-INF/dummy_unique_name.txt");

        performChecks(mountableFile);
    }

}