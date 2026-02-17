class DummyClass_76724 {
    @Test
    public void testNonAsciiDir() throws Exception {
        final File testDir = initProject("projects/classic", "projects/ÄÅ¡ÄÅÅ¾Ã½Ã¡Ã­Ã©Å¯Ãº");
        final RunningInvoker running = new RunningInvoker(testDir, false);

        final MavenProcessInvocationResult result = running.execute(Arrays.asList("install", "-DskipTests"),
                Collections.emptyMap());
        await().atMost(1, TimeUnit.MINUTES).until(() -> result.getProcess() != null && !result.getProcess().isAlive());
        assertThat(running.log()).containsIgnoringCase("BUILD SUCCESS");
        running.stop();

        File output = new File(testDir, "target/output.log");
        output.createNewFile();

        Process process = doLaunch(new File(testDir, "target/quarkus-app"), Paths.get("quarkus-run.jar"), output,
                Collections.emptyList()).start();
        try {
            // Wait until server up
            dumpFileContentOnFailure(() -> {
                await().pollDelay(1, TimeUnit.SECONDS)
                        .atMost(1, TimeUnit.MINUTES).until(() -> DevModeTestUtils.getHttpResponse("/app/hello/package", 200));
                return null;
            }, output, ConditionTimeoutException.class);
        } finally {
            process.destroy();
        }

    }

}