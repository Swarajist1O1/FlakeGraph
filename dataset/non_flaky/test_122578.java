class DummyClass_122578 {
    @Test
    public void regularFile() {
        path.createParents().writeUtf8File("file content");
        fileSnapshot = fileSnapshot.snapshot();
        assertTrue(fileSnapshot.exists());
        assertTrue(fileSnapshot.attributes().isPresent());
        assertTrue(fileSnapshot.attributes().get().isRegularFile());
        assertTrue(fileSnapshot.utf8Content().isPresent());
        assertEquals("file content", fileSnapshot.utf8Content().get());

        FileSnapshot newFileSnapshot = fileSnapshot.snapshot();
        assertSame(fileSnapshot, newFileSnapshot);
    }

}