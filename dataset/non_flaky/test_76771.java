class DummyClass_76771 {
    @Test
    public void testQuarkusIndexDependencyOnLocalModule() throws Exception {
        testDir = initProject("projects/quarkus-index-dependencies");

        running = new RunningInvoker(testDir, false);
        final MavenProcessInvocationResult result = running.execute(Collections.singletonList("package"),
                Collections.emptyMap());

        assertThat(result.getProcess().waitFor()).isEqualTo(0);

        final File targetDir = new File(testDir.getAbsoluteFile(), "runner" + File.separator + "target");

        final Path runnerJar = targetDir.toPath().resolve("quarkus-app").resolve("quarkus-run.jar");
        Assertions.assertTrue(Files.exists(runnerJar), "Runner jar " + runnerJar + " is missing");
        assertZipEntriesCanBeOpenedAndClosed(runnerJar);
    }

}