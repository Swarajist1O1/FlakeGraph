class DummyClass_76745 {
    @Test
    public void testProjectGeneration() throws MavenInvocationException, IOException {
        testDir = initEmptyProject("projects/project-generation");
        assertThat(testDir).isDirectory();
        invoker = initInvoker(testDir);

        Properties properties = new Properties();
        properties.put("outputDirectory", "jbang");
        InvocationResult result = setup(properties);

        assertThat(result.getExitCode()).isZero();
    }

}