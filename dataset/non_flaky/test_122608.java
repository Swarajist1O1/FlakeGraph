class DummyClass_122608 {
    @Test(expected = IllegalArgumentException.class)
    public void badPermissionsString() {
        new UnixPath(fs.getPath("file.txt")).setPermissions("abcdefghi");
    }

}