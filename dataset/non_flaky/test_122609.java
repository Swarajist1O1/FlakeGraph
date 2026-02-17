class DummyClass_122609 {
    @Test
    public void owner() {
        Path path = fs.getPath("file.txt");
        UnixPath unixPath = new UnixPath(path);
        unixPath.writeUtf8File("foo");

        unixPath.setOwner("owner");
        assertEquals("owner", unixPath.getOwner());

        unixPath.setGroup("group");
        assertEquals("group", unixPath.getGroup());
    }

}