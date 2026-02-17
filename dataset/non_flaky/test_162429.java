class DummyClass_162429 {
    @Test
    public void customClasspathResourceMappingTest() throws IOException {
        // Note: This functionality doesn't work if you are running your build inside a Docker container;
        // in that case this test will fail.
        String line = getReaderForContainerPort80(alpineClasspathResource).readLine();

        assertEquals("Resource on the classpath can be mapped using calls to withClasspathResourceMapping", "FOOBAR", line);
    }

}