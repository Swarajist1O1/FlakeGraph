class DummyClass_76754 {
    @Test
    public void testProjectGenerationFromScratchWithMissingExtensionShouldFail() throws Exception {
        testDir = initEmptyProject("projects/project-generation-with-missing-extension");
        assertThat(testDir).isDirectory();
        invoker = initInvoker(testDir);

        Properties properties = new Properties();
        properties.put("projectGroupId", "org.acme");
        properties.put("projectArtifactId", "acme");
        properties.put("className", "org.acme.MyResource");
        properties.put("extensions", "resteasy,smallrye-metrics,missing");
        InvocationResult result = setup(properties);

        assertThat(result.getExitCode()).isOne();
    }

}