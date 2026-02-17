class DummyClass_76758 {
    @Test
    public void testProjectGenerationFromScratchWithAppConfigParameter() throws MavenInvocationException, IOException {
        testDir = initEmptyProject("projects/project-generation-with-config-param");
        assertThat(testDir).isDirectory();
        invoker = initInvoker(testDir);

        Properties properties = new Properties();
        properties.put("projectGroupId", "org.acme");
        properties.put("projectArtifactId", "acme");
        properties.put("projectVersion", "1.0.0-SNAPSHOT");

        List<String> configs = Arrays.asList("custom.app.config1=val1",
                "custom.app.config2=val2", "lib.config=val3");
        properties.put("appConfig", StringUtils.join(configs, ", "));

        InvocationResult result = setup(properties);

        assertThat(result.getExitCode()).isZero();

        // As the directory is not empty (log) navigate to the artifactID directory
        testDir = new File(testDir, "acme");

        assertThat(new File(testDir, "pom.xml")).isFile();
        assertThat(new File(testDir, "src/main/java")).isDirectory();

        String file = Files
                .asCharSource(new File(testDir, "src/main/resources/application.properties"), Charsets.UTF_8)
                .read();
        configs.forEach(conf -> Assertions.assertTrue(file.contains(conf)));

    }

}