class DummyClass_122610 {
    @Test
    public void createDirectoryWithPermissions() {
        Path path = fs.getPath("dir");
        UnixPath unixPath = new UnixPath(path);
        String permissions = "rwxr-xr--";
        unixPath.createDirectory(permissions);
        assertTrue(unixPath.isDirectory());
        assertEquals(permissions, unixPath.getPermissions());
    }

}