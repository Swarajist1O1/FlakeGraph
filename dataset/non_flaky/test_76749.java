class DummyClass_76749 {
    @Test
    public void testGradleProjectGenerationWithExistingGradleFileShouldFail() throws Exception {
        testDir = initProject("projects/parent-gradle-it", "projects/gradle-project-generation-from-parent-gradle");
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