class DummyClass_122607 {
    @Test
    public void permissions() {
        String expectedPermissions = "rwxr-x---";
        UnixPath path = new UnixPath(fs.getPath("file.txt"));
        path.writeUtf8File("foo");
        path.setPermissions(expectedPermissions);
        assertEquals(expectedPermissions, path.getPermissions());
    }

}