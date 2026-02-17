class DummyClass_76747 {
    @Test
    public void testProjectGenerationWithExistingPomFileWithPackagingJarShouldFail() throws Exception {
        testDir = initProject("projects/simple-pom-it", "projects/project-generation-from-empty-pom");
        assertThat(testDir).isDirectory();
        invoker = initInvoker(testDir);
        InvocationResult result = setup(new Properties());

        assertThat(result.getExitCode()).isOne();
    }

}