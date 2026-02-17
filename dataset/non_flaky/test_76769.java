class DummyClass_76769 {
    @Test
    public void testLegacyJarHasValidCRC() throws Exception {
        testDir = initProject("projects/uberjar-check", "projects/project-legacyjar-crc");

        running = new RunningInvoker(testDir, false);
        final MavenProcessInvocationResult result = running.execute(Collections.singletonList("package"),
                Collections.singletonMap("QUARKUS_PACKAGE_TYPE", "legacy-jar"));

        assertThat(result.getProcess().waitFor()).isEqualTo(0);

        final File targetDir = getTargetDir();
        assertThat(getNumberOfFilesEndingWith(targetDir, ".jar")).isEqualTo(2);

        final Path runnerJar = targetDir.toPath().resolve("acme-1.0-SNAPSHOT-runner.jar");
        Assertions.assertTrue(Files.exists(runnerJar), "Runner jar " + runnerJar + " is missing");
        assertZipEntriesCanBeOpenedAndClosed(runnerJar);
    }

}