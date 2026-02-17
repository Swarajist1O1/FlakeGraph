class DummyClass_76764 {
    @Test
    public void testQuarkusPackageOutputDirectory()
            throws MavenInvocationException, IOException, InterruptedException {
        testDir = initProject("projects/quarkus.package.output-directory");

        running = new RunningInvoker(testDir, false);
        // we do want to run the tests too
        final MavenProcessInvocationResult result = running.execute(Collections.singletonList("package"),
                Collections.emptyMap());

        assertThat(result.getProcess().waitFor()).isEqualTo(0);

        File targetDir = getTargetDir();
        List<File> jars = getFilesEndingWith(targetDir, ".jar");
        assertThat(jars).hasSize(1);

        targetDir = new File(targetDir, "custom-output-dir");
        assertThat(targetDir).exists();
        jars = getFilesEndingWith(targetDir, ".jar");
        assertThat(jars).hasSize(1);
    }

}