class DummyClass_76738 {
    @Test
    public void testCreateStandaloneExtension(TestInfo testInfo) throws Throwable {
        testDir = initEmptyProject("output/create-standalone-extension");
        assertThat(testDir).isDirectory();
        invoker = initInvoker(testDir);

        Properties properties = new Properties();
        properties.put("groupId", "io.standalone");
        properties.put("extensionId", "my-own-ext");
        properties.put("namespaceId", "my-org-");
        properties.put("quarkusVersion", "1.10.5.Final");
        InvocationResult result = setup(properties);

        assertThat(result.getExitCode()).isZero();

        final Path testDirPath = testDir.toPath();
        assertThatDirectoryTreeMatchSnapshots(testInfo, testDirPath)
                .contains(
                        "my-org-my-own-ext/pom.xml",
                        "my-org-my-own-ext/deployment/src/main/java/io/standalone/my/own/ext/deployment/MyOwnExtProcessor.java",
                        "my-org-my-own-ext/integration-tests/pom.xml",
                        "my-org-my-own-ext/integration-tests/src/test/java/io/standalone/my/own/ext/it/MyOwnExtResourceTest.java");
        assertThatMatchSnapshot(testInfo, testDirPath, "my-org-my-own-ext/pom.xml");
        assertThatMatchSnapshot(testInfo, testDirPath, "my-org-my-own-ext/runtime/pom.xml");
    }

}