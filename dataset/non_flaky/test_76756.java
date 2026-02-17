class DummyClass_76756 {
    @Test
    public void testGradleProjectGenerationFromScratchWithExtensions() throws Exception {
        testDir = initEmptyProject("projects/gradle-project-generation-with-extensions");
        assertThat(testDir).isDirectory();
        invoker = initInvoker(testDir);

        Properties properties = new Properties();
        properties.put("projectGroupId", "org.acme");
        properties.put("projectArtifactId", "acme");
        properties.put("className", "org.acme.MyResource");
        properties.put("extensions", "kotlin,resteasy,jackson");
        properties.put("buildTool", "gradle");
        InvocationResult result = setup(properties);

        assertThat(result.getExitCode()).isZero();

        // As the directory is not empty (log) navigate to the artifactID directory
        testDir = new File(testDir, "acme");

        assertThat(new File(testDir, "build.gradle")).isFile();
        assertThat(new File(testDir, "gradlew.bat")).isFile();
        assertThat(new File(testDir, "gradlew")).isFile();
        assertThat(new File(testDir, "gradle/wrapper")).isDirectory();
        assertThat(new File(testDir, "src/main/kotlin")).isDirectory();

        check(new File(testDir, "src/main/kotlin/org/acme/MyResource.kt"), "package org.acme");

        assertThat(FileUtils.readFileToString(new File(testDir, "build.gradle"), "UTF-8"))
                .contains("quarkus-kotlin", "quarkus-jackson").doesNotContain("missing");
    }

}