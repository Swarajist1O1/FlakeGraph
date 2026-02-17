class DummyClass_162430 {
    @Test
    public void customClasspathResourceMappingWithSelinuxTest() throws IOException {
        String line = getReaderForContainerPort80(alpineClasspathResourceSelinux).readLine();
        assertEquals("Resource on the classpath can be mapped using calls to withClasspathResourceMappingSelinux", "FOOBAR", line);
    }

}