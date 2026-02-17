class DummyClass_76761 {
    @Test
    public void generateNewProjectAndRun() throws Exception {
        testDir = initEmptyProject("projects/project-generation-and-run");

        // Scaffold the new project
        assertThat(testDir).isDirectory();
        invoker = initInvoker(testDir);

        Properties properties = new Properties();
        properties.put("projectGroupId", "org.acme");
        properties.put("projectArtifactId", "acme");
        properties.put("extensions", "resteasy");
        properties.put("className", "org.acme.HelloResource");
        InvocationResult result = setup(properties);

        assertThat(result.getExitCode()).isZero();

        // Run
        // As the directory is not empty (log) navigate to the artifactID directory
        testDir = new File(testDir, "acme");
        running = new RunningInvoker(testDir, false);
        final Properties mvnRunProps = new Properties();
        mvnRunProps.setProperty("debug", "false");
        running.execute(Arrays.asList("compile", "quarkus:dev"), Collections.emptyMap(), mvnRunProps);

        String resp = DevModeTestUtils.getHttpResponse();

        assertThat(resp).containsIgnoringCase("ready").containsIgnoringCase("application").containsIgnoringCase("org.acme")
                .containsIgnoringCase("1.0.0-SNAPSHOT");

        String greeting = DevModeTestUtils.getHttpResponse("/hello");
        assertThat(greeting).containsIgnoringCase("hello");
    }

}