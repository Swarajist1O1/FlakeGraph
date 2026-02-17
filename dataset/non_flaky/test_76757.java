class DummyClass_76757 {
    @Test
    public void testProjectGenerationFromScratchWithCustomDependencies() throws Exception {
        testDir = initEmptyProject("projects/project-generation-with-resource-and-custom-deps");
        assertThat(testDir).isDirectory();
        invoker = initInvoker(testDir);

        Properties properties = new Properties();
        properties.put("projectGroupId", "org.acme");
        properties.put("projectArtifactId", "acme");
        properties.put("className", "org.acme.MyResource");
        properties.put("extensions", "resteasy,commons-io:commons-io:2.5");
        InvocationResult result = setup(properties);

        assertThat(result.getExitCode()).isZero();

        // As the directory is not empty (log) navigate to the artifactID directory
        testDir = new File(testDir, "acme");

        assertThat(new File(testDir, "pom.xml")).isFile();
        assertThat(new File(testDir, "src/main/java/org/acme/MyResource.java")).isFile();
        assertThat(FileUtils.readFileToString(new File(testDir, "pom.xml"), "UTF-8"))
                .contains("commons-io");

        Model model = loadPom(testDir);
        assertThat(model.getDependencyManagement().getDependencies().stream()
                .anyMatch(d -> d.getArtifactId().equals(MojoUtils.TEMPLATE_PROPERTY_QUARKUS_PLATFORM_ARTIFACT_ID_VALUE)
                        && d.getVersion().equals(MojoUtils.TEMPLATE_PROPERTY_QUARKUS_PLATFORM_VERSION_VALUE)
                        && d.getScope().equals("import")
                        && d.getType().equals("pom"))).isTrue();

        assertThat(
                model.getDependencies().stream().anyMatch(d -> d.getArtifactId().equalsIgnoreCase("quarkus-resteasy")
                        && d.getVersion() == null)).isTrue();

        assertThat(model.getDependencies().stream().anyMatch(d -> d.getArtifactId().equalsIgnoreCase("commons-io")
                && d.getVersion().equalsIgnoreCase("2.5"))).isTrue();
    }

}