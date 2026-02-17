class DummyClass_76733 {
    @Test
    public void testThatAppCDSAreUsable() throws Exception {
        File testDir = initProject("projects/classic", "projects/project-classic-console-output-appcds");
        RunningInvoker running = new RunningInvoker(testDir, false);

        MavenProcessInvocationResult result = running
                .execute(Arrays.asList("package", "-DskipTests", "-Dquarkus.package.create-appcds=true"),
                        Collections.emptyMap());

        await().atMost(1, TimeUnit.MINUTES).until(() -> result.getProcess() != null && !result.getProcess().isAlive());
        assertThat(running.log()).containsIgnoringCase("BUILD SUCCESS");
        running.stop();

        Path jar = testDir.toPath().toAbsolutePath()
                .resolve(Paths.get("target/quarkus-app/quarkus-run.jar"));
        File output = new File(testDir, "target/output.log");
        output.createNewFile();

        // by using '-Xshare:on' we ensure that the JVM will fail if for any reason is cannot use the AppCDS
        // '-Xlog:class+path=info' will print diagnostic information that is useful for debugging if something goes wrong
        Process process = doLaunch(jar.getFileName(), output,
                Arrays.asList("-XX:SharedArchiveFile=app-cds.jsa", "-Xshare:on", "-Xlog:class+path=info"))
                        .directory(jar.getParent().toFile()).start();
        try {
            // Wait until server up
            dumpFileContentOnFailure(() -> {
                await()
                        .pollDelay(1, TimeUnit.SECONDS)
                        .atMost(1, TimeUnit.MINUTES).until(() -> DevModeTestUtils.getHttpResponse("/app/hello/package", 200));
                return null;
            }, output, ConditionTimeoutException.class);

            String logs = FileUtils.readFileToString(output, "UTF-8");

            assertThatOutputWorksCorrectly(logs);
        } finally {
            process.destroy();
        }

    }

}