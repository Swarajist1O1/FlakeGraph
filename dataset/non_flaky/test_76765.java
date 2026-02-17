class DummyClass_76765 {
    @Test
    public void testDependencyOnPomMutableJar()
            throws MavenInvocationException, IOException, InterruptedException {
        testDir = initProject("projects/dependency-on-pom");

        running = new RunningInvoker(testDir, false);
        // we do want to run the tests too
        final MavenProcessInvocationResult result = running.execute(Collections.singletonList("package"),
                Collections.emptyMap());

        assertThat(result.getProcess().waitFor()).isEqualTo(0);

        File targetDir = getTargetDir();
        List<File> jars = getFilesEndingWith(targetDir, ".jar");
        assertThat(jars).hasSize(1);
    }

}