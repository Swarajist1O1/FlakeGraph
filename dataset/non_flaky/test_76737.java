class DummyClass_76737 {
    @Test
    public void testCreateQuarkiverseExtension(TestInfo testInfo) throws Throwable {
        testDir = initEmptyProject("output/create-quarkiverse-extension");
        assertThat(testDir).isDirectory();
        invoker = initInvoker(testDir);

        Properties properties = new Properties();
        properties.put("groupId", "io.quarkiverse.my-quarki-ext");
        properties.put("extensionId", "my-quarki-ext");
        properties.put("quarkusVersion", "1.10.5.Final");
        InvocationResult result = setup(properties);

        assertThat(result.getExitCode()).isZero();

        final Path testDirPath = testDir.toPath();
        assertThatDirectoryTreeMatchSnapshots(testInfo, testDirPath)
                .contains(
                        "quarkus-my-quarki-ext/pom.xml",
                        "quarkus-my-quarki-ext/deployment/src/main/java/io/quarkiverse/my/quarki/ext/deployment/MyQuarkiExtProcessor.java",
                        "quarkus-my-quarki-ext/integration-tests/pom.xml",
                        "quarkus-my-quarki-ext/integration-tests/src/test/java/io/quarkiverse/my/quarki/ext/it/MyQuarkiExtResourceTest.java");
        assertThatMatchSnapshot(testInfo, testDirPath, "quarkus-my-quarki-ext/pom.xml");
        assertThatMatchSnapshot(testInfo, testDirPath, "quarkus-my-quarki-ext/runtime/pom.xml");
    }

}