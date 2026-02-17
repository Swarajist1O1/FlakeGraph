class DummyClass_162386 {
    @Test
    public void forHostPathWithSpaces() throws Exception {
        final Path file = createTempFile("some path");
        final MountableFile mountableFile = MountableFile.forHostPath(file.toString());

        performChecks(mountableFile);

        assertTrue("The resolved path contains the original space", mountableFile.getResolvedPath().contains(" "));
        assertFalse("The resolved path does not contain an escaped space", mountableFile.getResolvedPath().contains("\\ "));
    }

}