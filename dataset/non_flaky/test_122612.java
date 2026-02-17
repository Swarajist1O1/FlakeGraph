class DummyClass_122612 {
    @Test
    public void readBytesIfExists() {
        UnixPath path = new UnixPath(fs.getPath("example.txt"));
        assertFalse(path.readBytesIfExists().isPresent());
        path.writeBytes(new byte[]{42});
        assertArrayEquals(new byte[]{42}, path.readBytesIfExists().get());
    }

}