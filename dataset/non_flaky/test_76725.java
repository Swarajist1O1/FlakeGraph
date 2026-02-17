class DummyClass_76725 {
    @Test
    public void testThatJarRunnerConsoleOutputWorksCorrectly() throws MavenInvocationException, IOException {
        File testDir = initProject("projects/classic", "projects/project-classic-console-output");
        RunningInvoker running = new RunningInvoker(testDir, false);

        MavenProcessInvocationResult result = running.execute(Arrays.asList("package", "-DskipTests"), Collections.emptyMap());
        await().atMost(1, TimeUnit.MINUTES).until(() -> result.getProcess() != null && !result.getProcess().isAlive());
        assertThat(running.log()).containsIgnoringCase("BUILD SUCCESS");
        running.stop();

        Path jar = testDir.toPath().toAbsolutePath()
                .resolve(Paths.get("target/quarkus-app/quarkus-run.jar"));
        File output = new File(testDir, "target/output.log");
        output.createNewFile();

        Process process = doLaunch(jar, output).start();
        try {
            // Wait until server up
            await()
                    .pollDelay(1, TimeUnit.SECONDS)
                    .atMost(1, TimeUnit.MINUTES).until(() -> DevModeTestUtils.getHttpResponse("/app/hello/package", 200));

            String logs = FileUtils.readFileToString(output, "UTF-8");

            assertThatOutputWorksCorrectly(logs);

            // test that the application name and version are properly set
            assertApplicationPropertiesSetCorrectly();
            assertResourceReadingFromClassPathWorksCorrectly("");
            assertUsingProtectionDomainWorksCorrectly("");
        } finally {
            process.destroy();
        }

    }

}