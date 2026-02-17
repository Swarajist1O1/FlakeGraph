class DummyClass_76748 {
    @Test
    public void testProjectGenerationWithExistingGradleFileShouldFail() throws Exception {
        testDir = initProject("projects/parent-gradle-it", "projects/project-generation-from-parent-gradle");
        assertThat(testDir).isDirectory();
        invoker = initInvoker(testDir);
        InvocationResult result = setup(new Properties());

        assertThat(result.getExitCode()).isOne();
    }

}