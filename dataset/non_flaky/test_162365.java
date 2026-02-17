class DummyClass_162365 {
    @Test
    public void testPackages() throws Exception {
        assertThatFileList(root).containsOnly(
                "docker-java.properties",
                "org",
                "META-INF",
                "com"
        );

        assertThatFileList(root.resolve("org")).containsOnly(
                "testcontainers"
        );

        assertThatFileList(root.resolve("com")).containsOnly(
                "github"
        );

        assertThatFileList(root.resolve("com").resolve("github")).containsOnly(
                "dockerjava"
        );
    }

}