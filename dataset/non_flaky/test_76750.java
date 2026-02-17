class DummyClass_76750 {
    @Test
    public void testGradleProjectGenerationWithExistingPomFileShouldFail() throws Exception {
        testDir = initProject("projects/parent-pom-it", "projects/gradle-project-generation-from-parent-pom");
        assertThat(testDir).isDirectory();
        invoker = initInvoker(testDir);
        Properties properties = new Properties();
        properties.put("projectGroupId", "org.acme");
        properties.put("projectArtifactId", "acme");
        properties.put("className", "org.acme.MyResource");
        properties.put("buildTool", "gradle");
        InvocationResult result = setup(properties);

        assertThat(result.getExitCode()).isOne();
    }

}