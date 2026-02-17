class DummyClass_76734 {
    @Test
    public void testArcExcludeDependencyOnLocalModule() throws Exception {
        File testDir = initProject("projects/arc-exclude-dependencies");
        RunningInvoker running = new RunningInvoker(testDir, false);

        MavenProcessInvocationResult result = running.execute(Arrays.asList("package", "-DskipTests"), Collections.emptyMap());
        await().atMost(1, TimeUnit.MINUTES).until(() -> result.getProcess() != null && !result.getProcess().isAlive());
        assertThat(running.log()).containsIgnoringCase("BUILD SUCCESS");
        running.stop();

        File targetDir = new File(testDir.getAbsoluteFile(), "runner" + File.separator + "target");
        Path jar = targetDir.toPath().toAbsolutePath()
                .resolve(Paths.get("quarkus-app/quarkus-run.jar"));
        File output = new File(targetDir, "output.log");
        output.createNewFile();

        Process process = doLaunch(jar, output).start();
        try {
            // Wait until server up
            AtomicReference<String> response = new AtomicReference<>();
            await()
                    .pollDelay(1, TimeUnit.SECONDS)
                    .atMost(1, TimeUnit.MINUTES).until(() -> {
                        String ret = DevModeTestUtils.getHttpResponse("/hello", true);
                        response.set(ret);
                        return ret.contains("hello:");
                    });

            // Test that bean is not resolvable
            assertThat(response.get()).containsIgnoringCase("hello:false");
        } finally {
            process.destroy();
        }
    }

}