class DummyClass_162367 {
    @Test
    public void testMetaInfServices() throws Exception {
        assertThatFileList(root.resolve("META-INF").resolve("services"))
                .allMatch(it -> it.startsWith("org.testcontainers."));
    }

}