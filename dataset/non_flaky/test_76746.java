class DummyClass_76746 {
    @Test
    public void testProjectGenerationFromScratch() throws MavenInvocationException, IOException {
        testDir = initEmptyProject("projects/project-generation");
        assertThat(testDir).isDirectory();
        invoker = initInvoker(testDir);

        Properties properties = new Properties();
        properties.put("projectGroupId", "org.acme");
        properties.put("projectArtifactId", "acme");
        properties.put("projectVersion", "1.0.0-SNAPSHOT");

        InvocationResult result = setup(properties);

        assertThat(result.getExitCode()).isZero();

        // As the directory is not empty (log) navigate to the artifactID directory
        testDir = new File(testDir, "acme");

        assertThat(new File(testDir, "pom.xml")).isFile();
        assertThat(new File(testDir, "src/main/java")).isDirectory();
        assertThat(new File(testDir, "src/main/resources/application.properties")).isFile();

        String config = Files
                .asCharSource(new File(testDir, "src/main/resources/application.properties"), Charsets.UTF_8)
                .read();
        assertThat(config).isEmpty();

        assertThat(new File(testDir, "src/main/docker/Dockerfile.native")).isFile();
        assertThat(new File(testDir, "src/main/docker/Dockerfile.jvm")).isFile();

        Model model = loadPom(testDir);
        final DependencyManagement dependencyManagement = model.getDependencyManagement();
        final List<Dependency> dependencies = dependencyManagement.getDependencies();
        assertThat(dependencies.stream()
                .anyMatch(d -> d.getArtifactId().equals(MojoUtils.TEMPLATE_PROPERTY_QUARKUS_PLATFORM_ARTIFACT_ID_VALUE)
                        && d.getVersion().equals(MojoUtils.TEMPLATE_PROPERTY_QUARKUS_PLATFORM_VERSION_VALUE)
                        && d.getScope().equals("import")
                        && d.getType().equals("pom"))).isTrue();

        assertThat(
                model.getDependencies().stream().anyMatch(d -> d.getArtifactId().equalsIgnoreCase("quarkus-resteasy")
                        && d.getVersion() == null)).isTrue();

        assertThat(model.getProfiles()).hasSize(1);
        assertThat(model.getProfiles().get(0).getId()).isEqualTo("native");

        Xpp3Dom surefireSystemProperties = Optional.ofNullable(model.getBuild())
                .map(Build::getPlugins)
                .flatMap(plugins -> plugins.stream().filter(p -> p.getArtifactId().equals("maven-surefire-plugin")).findFirst())
                .map(Plugin::getConfiguration)
                .map(Xpp3Dom.class::cast)
                .map(cfg -> cfg.getChild("systemPropertyVariables"))
                .orElse(null);
        assertThat(surefireSystemProperties).isNotNull();
        assertThat(surefireSystemProperties.getChild("java.util.logging.manager"))
                .returns(LogManager.class.getName(), from(Xpp3Dom::getValue));
        assertThat(surefireSystemProperties.getChild("maven.home"))
                .returns("${maven.home}", from(Xpp3Dom::getValue));
    }

}