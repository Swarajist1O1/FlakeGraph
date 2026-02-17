class DummyClass_76759 {
    @Test
    public void testThatDefaultPackageAreReplaced() throws Exception {
        testDir = initEmptyProject("projects/default-package-test");
        assertThat(testDir).isDirectory();
        invoker = initInvoker(testDir);

        Properties properties = new Properties();
        properties.put("className", "MyGreatResource");
        properties.put("extensions", "resteasy");
        InvocationResult result = setup(properties);

        assertThat(result.getExitCode()).isZero();
        // As the directory is not empty (log) navigate to the artifactID directory
        testDir = new File(testDir, "code-with-quarkus");
        check(new File(testDir, "src/main/java/org/acme/MyGreatResource.java"),
                "package org.acme;");
    }

}