class DummyClass_162383 {
    @Test
    public void forClasspathResourceFromJar() throws Exception {
        final MountableFile mountableFile = MountableFile.forClasspathResource("META-INF/dummy_unique_name.txt");

        performChecks(mountableFile);
    }

}