class DummyClass_76755 {
    @Test
    public void testProjectGenerationFromScratchWithExtensions() throws Exception {
        testDir = initEmptyProject("projects/project-generation-with-resources-and-extension");
        assertThat(testDir).isDirectory();
        invoker = initInvoker(testDir);

        Properties properties = new Properties();
        properties.put("projectGroupId", "org.acme");
        properties.put("projectArtifactId", "acme");
        properties.put("className", "org.acme.MyResource");
        properties.put("extensions", "resteasy,smallrye-metrics");
        InvocationResult result = setup(properties);

        assertThat(result.getExitCode()).isZero();

        // As the directory is not empty (log) navigate to the artifactID directory
        testDir = new File(testDir, "acme");

        assertThat(new File(testDir, "pom.xml")).isFile();
        assertThat(new File(testDir, "src/main/java")).isDirectory();

        check(new File(testDir, "src/main/java/org/acme/MyResource.java"), "package org.acme;");

        assertThat(FileUtils.readFileToString(new File(testDir, "pom.xml"), "UTF-8"))
                .contains("quarkus-resteasy", "quarkus-smallrye-metrics").doesNotContain("missing");

        Model model = loadPom(testDir);
        assertThat(model.getDependencyManagement().getDependencies().stream()
                .anyMatch(d -> d.getArtifactId().equals(MojoUtils.TEMPLATE_PROPERTY_QUARKUS_PLATFORM_ARTIFACT_ID_VALUE)
                        && d.getVersion().equals(MojoUtils.TEMPLATE_PROPERTY_QUARKUS_PLATFORM_VERSION_VALUE)
                        && d.getScope().equals("import")
                        && d.getType().equals("pom"))).isTrue();

        assertThat(
                model.getDependencies().stream().anyMatch(d -> d.getArtifactId().equalsIgnoreCase("quarkus-resteasy")
                        && d.getVersion() == null)).isTrue();

        assertThat(model.getDependencies().stream()
                .anyMatch(d -> d.getArtifactId().equalsIgnoreCase("quarkus-smallrye-metrics")
                        && d.getVersion() == null)).isTrue();
    }

}