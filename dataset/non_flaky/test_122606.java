class DummyClass_122606 {
    @Test
    public void utf8File() {
        String original = "foo\nbar\n";
        UnixPath path = new UnixPath(fs.getPath("example.txt"));
        path.writeUtf8File(original);
        String fromFile = path.readUtf8File();
        assertEquals(original, fromFile);
    }

}