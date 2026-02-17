class DummyClass_76743 {
    @Test
    public void testThatApplicationRecoversCompilationIssue() throws MavenInvocationException, IOException {
        testDir = initProject("projects/classic-remote-dev", "projects/project-classic-run-compilation-issue-remote");
        agentDir = initProject("projects/classic-remote-dev", "projects/project-classic-run-compilation-issue-local");
        runAndCheck();

        // Edit the "Hello" message.
        File source = new File(agentDir, "src/main/java/org/acme/HelloResource.java");
        String uuid = UUID.randomUUID().toString();
        filter(source, Collections.singletonMap("return \"hello\";", "return \"" + uuid + "\"")); // No semi-colon

        // Wait until we get "uuid"
        AtomicReference<String> last = new AtomicReference<>();
        await()
                .pollDelay(1, TimeUnit.SECONDS)
                .atMost(1, TimeUnit.MINUTES).until(() -> {
                    String content = DevModeTestUtils.getHttpResponse("/app/hello", true);
                    last.set(content);
                    return content.contains(uuid);
                });

        assertThat(last.get()).containsIgnoringCase("error")
                .containsIgnoringCase("return \"" + uuid + "\"")
                .containsIgnoringCase("compile");

        await()
                .pollDelay(1, TimeUnit.SECONDS)
                .pollInterval(1, TimeUnit.SECONDS)
                .until(source::isFile);
        filter(source, Collections.singletonMap("\"" + uuid + "\"", "\"carambar\";"));

        // Wait until we get "uuid"
        await()
                .pollDelay(1, TimeUnit.SECONDS)
                .atMost(1, TimeUnit.MINUTES).until(() -> DevModeTestUtils.getHttpResponse("/app/hello").contains("carambar"));
    }

}