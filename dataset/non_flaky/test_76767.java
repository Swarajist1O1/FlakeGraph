class DummyClass_76767 {
    @Test
    public void testCustomPackaging()
            throws Exception {
        testDir = getTargetDir("projects/custom-packaging-plugin");

        running = new RunningInvoker(testDir, false);
        MavenProcessInvocationResult result = running.execute(Collections.singletonList("install"),
                Collections.emptyMap());
        assertThat(result.getProcess().waitFor()).isEqualTo(0);

        testDir = getTargetDir("projects/custom-packaging-app");

        running = new RunningInvoker(testDir, false);
        result = running.execute(Collections.singletonList("package"),
                Collections.emptyMap());
        assertThat(result.getProcess().waitFor()).isEqualTo(0);

        final File targetDir = getTargetDir();
        final File[] files = targetDir.listFiles(f -> f.getName().endsWith(".jar"));
        Set<String> jarNames = new HashSet<>(files.length);
        for (File f : files) {
            jarNames.add(f.getName());
        }

        final Path runnerJar = getTargetDir().toPath().resolve("quarkus-app").resolve("quarkus-run.jar");
        Assertions.assertTrue(Files.exists(runnerJar), "Runner jar " + runnerJar + " is missing");
        assertZipEntriesCanBeOpenedAndClosed(runnerJar);
    }

}