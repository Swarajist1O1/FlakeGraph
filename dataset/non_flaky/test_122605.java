class DummyClass_122605 {
    @Test
    public void createParents() {
        Path parentDirectory = fs.getPath("/a/b/c");
        Path filePath = parentDirectory.resolve("bar");
        UnixPath path = new UnixPath(filePath);

        assertFalse(Files.exists(fs.getPath("/a")));
        path.createParents();
        assertTrue(Files.exists(parentDirectory));
    }

}