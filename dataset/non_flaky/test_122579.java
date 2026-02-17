class DummyClass_122579 {
    @Test
    public void fileRemoval() {
        path.createParents().writeUtf8File("file content");
        fileSnapshot = fileSnapshot.snapshot();
        assertTrue(fileSnapshot.exists());
        path.deleteIfExists();
        fileSnapshot = fileSnapshot.snapshot();
        assertFalse(fileSnapshot.exists());
    }

}