class DummyClass_76768 {
    @Test
    public void testRunnerUberJarHasValidCRC() throws Exception {
        testDir = initProject("projects/uberjar-check", "projects/project-uberjar-crc");

        running = new RunningInvoker(testDir, false);

        Properties p = new Properties();
        p.setProperty("quarkus.package.type", "uber-jar");
        final MavenProcessInvocationResult result = running.execute(Collections.singletonList("package"),
                Collections.emptyMap(), p);
        assertThat(result.getProcess().waitFor()).isEqualTo(0);

        final File targetDir = getTargetDir();
        assertThat(getNumberOfFilesEndingWith(targetDir, ".jar")).isEqualTo(1);
        assertThat(getNumberOfFilesEndingWith(targetDir, ".original")).isEqualTo(1);

        final Path runnerJar = targetDir.toPath().resolve("acme-1.0-SNAPSHOT-runner.jar");
        Assertions.assertTrue(Files.exists(runnerJar), "Runner jar " + runnerJar + " is missing");
        assertZipEntriesCanBeOpenedAndClosed(runnerJar);
    }

}