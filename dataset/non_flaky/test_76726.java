class DummyClass_76726 {
    @Test
    public void testPlatformPropertiesOverridenInApplicationProperties() throws Exception {
        final File testDir = initProject("projects/platform-properties-overrides",
                "projects/platform-props-overriden-in-app-props");
        final RunningInvoker running = new RunningInvoker(testDir, false);

        final MavenProcessInvocationResult result = running.execute(Arrays.asList("install"),
                Collections.emptyMap());
        await().atMost(1, TimeUnit.MINUTES).until(() -> result.getProcess() != null && !result.getProcess().isAlive());
        assertThat(running.log()).containsIgnoringCase("BUILD SUCCESS");
        running.stop();

        File output = new File(testDir, "app/target/output.log");
        output.createNewFile();

        Process process = doLaunch(new File(testDir, "app/target/quarkus-app"), Paths.get("quarkus-run.jar"), output,
                Collections.emptyList()).start();
        try {
            Assertions.assertEquals("builder-image is customized", DevModeTestUtils.getHttpResponse("/hello"));
        } finally {
            process.destroy();
        }
    }

}