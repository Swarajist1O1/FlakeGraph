class DummyClass_76735 {
    @Test
    public void testCreateCoreExtension(TestInfo testInfo) throws Throwable {
        testDir = initProject("projects/create-extension-quarkus-core", "output/create-extension-quarkus-core");
        assertThat(testDir).isDirectory();
        invoker = initInvoker(testDir);

        Properties properties = new Properties();
        properties.put("extensionId", "my-ext");
        InvocationResult result = setup(properties);

        assertThat(result.getExitCode()).isZero();

        final Path testDirPath = testDir.toPath();
        assertThatDirectoryTreeMatchSnapshots(testInfo, testDirPath)
                .contains(
                        "extensions/my-ext/pom.xml",
                        "extensions/my-ext/runtime/src/main/resources/META-INF/quarkus-extension.yaml",
                        "extensions/my-ext/deployment/src/main/java/org/acme/my/ext/deployment/MyExtProcessor.java",
                        "integration-tests/my-ext/pom.xml",
                        "integration-tests/my-ext/src/test/java/org/acme/my/ext/it/MyExtResourceTest.java");
        assertThatMatchSnapshot(testInfo, testDirPath, "extensions/my-ext/pom.xml");
        assertThatMatchSnapshot(testInfo, testDirPath,
                "extensions/my-ext/runtime/src/main/resources/META-INF/quarkus-extension.yaml");
        assertThatMatchSnapshot(testInfo, testDirPath, "bom/application/pom.xml");
        assertThatMatchSnapshot(testInfo, testDirPath, "integration-tests/pom.xml");
        assertThatMatchSnapshot(testInfo, testDirPath, "extensions/pom.xml");
    }

}