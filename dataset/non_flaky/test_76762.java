class DummyClass_76762 {
    @Test
    public void testJavaLibraryPathAtRuntime() throws Exception {
        final File testDir = initProject("projects/native-image-app", "projects/native-image-app-output");
        final RunningInvoker running = new RunningInvoker(testDir, false);

        // trigger mvn package -Pnative -Dquarkus.ssl.native=true
        final String[] mvnArgs = new String[] { "package", "-DskipTests", "-Pnative", "-Dquarkus.ssl.native=true" };
        final MavenProcessInvocationResult result = running.execute(Arrays.asList(mvnArgs), Collections.emptyMap());
        await().atMost(10, TimeUnit.MINUTES).until(() -> result.getProcess() != null && !result.getProcess().isAlive());
        final String processLog = running.log();
        try {
            assertThat(processLog).containsIgnoringCase("BUILD SUCCESS");
        } catch (AssertionError ae) {
            // skip this test (instead of failing), if the native-image command wasn't available.
            // Bit brittle to rely on the log message, but it's OK in the context of this test
            Assumptions.assumeFalse(processLog.contains("Cannot find the `native-image"),
                    "Skipping test since native-image tool isn't available");
            // native-image command was available but the build failed for some reason, throw the original error
            throw ae;
        } finally {
            running.stop();
        }

        // now that the native image is built, run it
        final Path nativeImageRunner = testDir.toPath().toAbsolutePath().resolve(Paths.get("target/acme-1.0-SNAPSHOT-runner"));
        final Path tmpDir = Files.createTempDirectory("native-image-test");
        tmpDir.toFile().deleteOnExit();
        final Process nativeImageRunWithAdditionalLibPath = runNativeImage(nativeImageRunner,
                new String[] { "-Djava.library.path=" + tmpDir.toString() });
        try {
            final String response = DevModeTestUtils.getHttpResponse("/hello/javaLibraryPath");
            Assertions.assertTrue(response.contains(tmpDir.toString()),
                    "Response " + response + " for java.library.path was expected to contain the " + tmpDir + ", but didn't");
        } finally {
            nativeImageRunWithAdditionalLibPath.destroy();
        }

    }

}