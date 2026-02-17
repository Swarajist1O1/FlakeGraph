class DummyClass_76742 {
    @Test
    public void testThatNewResourcesAreServed() throws MavenInvocationException, IOException {
        testDir = initProject("projects/classic-remote-dev", "projects/project-classic-run-resource-change-remote");
        agentDir = initProject("projects/classic-remote-dev", "projects/project-classic-run-resource-change-local");
        runAndCheck();

        // Create a new resource
        File source = new File(agentDir, "src/main/resources/META-INF/resources/lorem.txt");
        FileUtils.write(source,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "UTF-8");
        await()
                .pollDelay(1, TimeUnit.SECONDS)
                .atMost(1, TimeUnit.MINUTES)
                .until(() -> DevModeTestUtils.getHttpResponse("/lorem.txt").contains("Lorem ipsum"));

        // Update the resource
        String uuid = UUID.randomUUID().toString();
        FileUtils.write(source, uuid, "UTF-8");
        await()
                .pollDelay(1, TimeUnit.SECONDS)
                .atMost(1, TimeUnit.MINUTES)
                .until(() -> DevModeTestUtils.getHttpResponse("/lorem.txt").contains(uuid));

        // Delete the resource
        //TODO: not supported yet in remote dev
        //        source.delete();
        //        await()
        //                .pollDelay(1, TimeUnit.SECONDS)
        //                .atMost(1, TimeUnit.MINUTES)
        //                .until(() -> getHttpResponse("/lorem.txt", 404));
    }

}