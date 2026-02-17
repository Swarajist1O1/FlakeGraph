class DummyClass_122615 {
    @Test
    public void testParentAndFilename() {
        var absolutePath = new UnixPath("/foo/bar");
        assertEquals("/foo", absolutePath.getParent().toString());
        assertEquals("bar", absolutePath.getFilename());

        var pathWithoutSlash = new UnixPath("foo");
        assertRuntimeException(IllegalStateException.class, "Path has no parent directory: 'foo'", () -> pathWithoutSlash.getParent());
        assertEquals("foo", pathWithoutSlash.getFilename());

        var pathWithSlash = new UnixPath("/foo");
        assertEquals("/", pathWithSlash.getParent().toString());
        assertEquals("foo", pathWithSlash.getFilename());

        assertRuntimeException(IllegalStateException.class, "Path has no parent directory: '/'", () -> new UnixPath("/").getParent());
        assertRuntimeException(IllegalStateException.class, "Path has no filename: '/'", () -> new UnixPath("/").getFilename());
    }

}