class DummyClass_122611 {
    @Test
    public void createSymbolicLink() {
        String original = "foo\nbar\n";
        UnixPath path = new UnixPath(fs.getPath("example.txt"));
        path.writeUtf8File(original);
        String fromFile = path.readUtf8File();
        assertEquals(original, fromFile);

        UnixPath link = path.createSymbolicLink(fs.getPath("link-to-example.txt"));
        assertEquals(original, link.readUtf8File());
    }

}