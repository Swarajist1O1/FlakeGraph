class DummyClass_122600 {
    @Test
    public void testAtomicWrite() {
        FileWriter writer = new FileWriter(fileSystem.getPath("/foo/bar"))
                .atomicWrite(true);

        assertTrue(writer.converge(context, "content"));

        verify(context).recordSystemModification(any(), eq("Creating file /foo/bar"));
        assertEquals("content", new UnixPath(writer.path()).readUtf8File());
    }

}