class DummyClass_76739 {
    @Test
    public void testThatTheApplicationIsReloadedOnJavaChange()
            throws MavenInvocationException, IOException, InterruptedException {
        testDir = initProject("projects/classic-remote-dev", "projects/project-classic-run-java-change-remote");
        agentDir = initProject("projects/classic-remote-dev", "projects/project-classic-run-java-change-local");
        runAndCheck();

        // Edit the "Hello" message.
        File source = new File(agentDir, "src/main/java/org/acme/HelloResource.java");
        String uuid = UUID.randomUUID().toString();
        filter(source, Collections.singletonMap("return \"hello\";", "return \"" + uuid + "\";"));

        // Wait until we get "uuid"
        await()
                .pollDelay(1, TimeUnit.SECONDS)
                .atMost(1, TimeUnit.MINUTES).until(() -> DevModeTestUtils.getHttpResponse("/app/hello").contains(uuid));

        await()
                .pollDelay(1, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .until(source::isFile);

        filter(source, Collections.singletonMap(uuid, "carambar"));

        // Wait until we get "carambar"
        await()
                .pollDelay(1, TimeUnit.SECONDS)
                .atMost(1, TimeUnit.MINUTES).until(() -> DevModeTestUtils.getHttpResponse("/app/hello").contains("carambar"));

        //also verify that the dev ui console is disabled
        DevModeTestUtils.getHttpResponse("/q/dev", 404, 10, TimeUnit.SECONDS);
    }

}