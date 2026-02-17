class DummyClass_76753 {
    @Test
    public void testProjectGenerationWithInvalidPackage() throws Exception {
        testDir = initEmptyProject("projects/project-generation-invalid-package");
        assertThat(testDir).isDirectory();
        invoker = initInvoker(testDir);

        Properties properties = new Properties();
        properties.put("projectGroupId", "org.acme");
        properties.put("projectArtifactId", "acme");
        properties.put("className", "org.acme.invalid-package-name.MyResource");

        InvocationResult result = setup(properties);

        assertThat(result.getExitCode()).isNotZero();
        assertThat(new File(testDir, "src/main/java/org/acme")).doesNotExist();
    }

}