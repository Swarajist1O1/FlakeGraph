class DummyClass_122614 {
    @Test
    public void atomicWrite() {
        var path = new UnixPath(fs.getPath("/dir/foo"));
        path.createParents();
        path.writeUtf8File("bar");
        path.atomicWriteUt8("bar v2");
        assertEquals("bar v2", path.readUtf8File());
    }

}