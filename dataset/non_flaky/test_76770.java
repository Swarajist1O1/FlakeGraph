class DummyClass_76770 {
    @Test
    public void testFastJarHasValidCRC() throws Exception {
        testDir = initProject("projects/uberjar-check", "projects/project-fastjar-crc");

        running = new RunningInvoker(testDir, false);
        final MavenProcessInvocationResult result = running.execute(Collections.singletonList("package"),
                Collections.emptyMap());

        assertThat(result.getProcess().waitFor()).isEqualTo(0);

        final Path runnerJar = getTargetDir().toPath().resolve("quarkus-app").resolve("quarkus-run.jar");
        Assertions.assertTrue(Files.exists(runnerJar), "Runner jar " + runnerJar + " is missing");
        assertZipEntriesCanBeOpenedAndClosed(runnerJar);
    }

}