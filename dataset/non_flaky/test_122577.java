class DummyClass_122577 {
    @Test
    public void directory() {
        path.createParents().createDirectory();
        fileSnapshot = fileSnapshot.snapshot();
        assertTrue(fileSnapshot.exists());
        assertTrue(fileSnapshot.attributes().isPresent());
        assertTrue(fileSnapshot.attributes().get().isDirectory());
    }

}