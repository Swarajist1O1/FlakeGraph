class DummyClass_76741 {
    @Test
    public void testThatTheApplicationIsReloadedOnConfigChange() throws MavenInvocationException, IOException {
        testDir = initProject("projects/classic-remote-dev", "projects/project-classic-run-config-change-remote");
        agentDir = initProject("projects/classic-remote-dev", "projects/project-classic-run-config-change-local");
        assertThat(testDir).isDirectory();
        runAndCheck();

        String resp = DevModeTestUtils.getHttpResponse();
        runningAgent = new RunningInvoker(agentDir, false);
        runningAgent.execute(Arrays.asList("compile", "quarkus:remote-dev"), Collections.emptyMap());

        assertThat(resp).containsIgnoringCase("ready").containsIgnoringCase("application").containsIgnoringCase("org.acme")
                .containsIgnoringCase("1.0-SNAPSHOT");

        String greeting = DevModeTestUtils.getHttpResponse("/app/hello/greeting");
        assertThat(greeting).containsIgnoringCase("bonjour");

        File source = new File(agentDir, "src/main/resources/application.properties");
        await()
                .pollDelay(1, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .until(source::isFile);

        String uuid = UUID.randomUUID().toString();
        filter(source, Collections.singletonMap("bonjour", uuid));

        // Wait until we get "uuid"
        await()
                .pollDelay(1, TimeUnit.SECONDS)
                .atMost(1, TimeUnit.MINUTES)
                .until(() -> DevModeTestUtils.getHttpResponse("/app/hello/greeting").contains(uuid));
    }

}