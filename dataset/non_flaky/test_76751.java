class DummyClass_76751 {
    @Test
    public void testProjectGenerationAsModuleWithExistingPomFileWithPackagingPom() throws Exception {
        testDir = initProject("projects/parent-pom-it", "projects/project-generation-from-parent-pom");
        assertThat(testDir).isDirectory();
        invoker = initInvoker(testDir);

        String projectArtifactId = "acme";
        Properties properties = new Properties();
        properties.put("projectGroupId", "io.acme.it");
        properties.put("projectArtifactId", projectArtifactId);
        properties.put("projectVersion", "1.0-SNAPSHOT");
        InvocationResult result = setup(properties);

        assertThat(result.getExitCode()).isZero();

        Model parentPomModel = loadPom(testDir);
        assertThat(parentPomModel.getModules()).isNotEmpty();
        assertThat(parentPomModel.getModules()).contains(projectArtifactId);

        Model modulePomModel = loadPom(new File(testDir, projectArtifactId));
        assertThat(modulePomModel.getParent()).isNotNull();
        assertThat(modulePomModel.getParent().getGroupId()).isEqualTo("io.acme.it");
        assertThat(modulePomModel.getParent().getArtifactId()).isEqualTo("acme-parent-pom");
        assertThat(modulePomModel.getParent().getVersion()).isEqualTo("0.0.1.BUILD-SNAPSHOT");
    }

}