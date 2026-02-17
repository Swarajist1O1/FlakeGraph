class DummyClass_76732 {
    @Test
    public void testThatLegacyJarFormatWorks() throws Exception {
        File testDir = initProject("projects/rr-with-json-logging", "projects/rr-with-json-logging-legacy-jar");
        RunningInvoker running = new RunningInvoker(testDir, false);

        MavenProcessInvocationResult result = running
                .execute(Arrays.asList("package",
                        "-DskipTests",
                        "-Dquarkus.package.type=legacy-jar"), Collections.emptyMap());

        await().atMost(1, TimeUnit.MINUTES).until(() -> result.getProcess() != null && !result.getProcess().isAlive());
        assertThat(running.log()).containsIgnoringCase("BUILD SUCCESS");
        running.stop();

        Path jar = testDir.toPath().toAbsolutePath()
                .resolve(Paths.get("target",
                        JarResultBuildStep.DEFAULT_FAST_JAR_DIRECTORY_NAME,
                        "quarkus-run.jar"));
        Assertions.assertFalse(Files.exists(jar));

        jar = testDir.toPath().toAbsolutePath()
                .resolve(Paths.get("target/acme-1.0-SNAPSHOT-runner.jar"));
        Assertions.assertTrue(Files.exists(jar));

        Properties quarkusArtifactProperties = new Properties();
        quarkusArtifactProperties
                .load(new FileInputStream(testDir.toPath().resolve("target").resolve("quarkus-artifact.properties").toFile()));
        Assertions.assertEquals("jar", quarkusArtifactProperties.get("type"));
        Assertions.assertEquals("acme-1.0-SNAPSHOT-runner.jar", quarkusArtifactProperties.get("path"));

        File output = new File(testDir, "target/output.log");
        output.createNewFile();

        Properties properties = new Properties();
        properties
                .load(new FileInputStream(testDir.toPath().resolve("target").resolve("quarkus-artifact.properties").toFile()));
        Assertions.assertEquals("jar", properties.get("type"));
        Assertions.assertEquals("acme-1.0-SNAPSHOT-runner.jar", properties.get("path"));

        Process process = doLaunch(jar, output).start();
        try {
            // Wait until server up
            dumpFileContentOnFailure(() -> {
                await()
                        .pollDelay(1, TimeUnit.SECONDS)
                        .atMost(1, TimeUnit.MINUTES).until(() -> DevModeTestUtils.getHttpResponse("/app/hello/package", 200));
                return null;
            }, output, ConditionTimeoutException.class);

            String logs = FileUtils.readFileToString(output, "UTF-8");

            assertThat(logs).isNotEmpty().contains("resteasy-reactive");

            // test that the application name and version are properly set
            assertApplicationPropertiesSetCorrectly();
            assertResourceReadingFromClassPathWorksCorrectly("");
            assertUsingProtectionDomainWorksCorrectly("");
        } finally {
            process.destroy();
        }
    }

}