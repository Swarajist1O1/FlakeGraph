class DummyClass_122576 {
    @Test
    public void fileDoesNotExist() {
        assertFalse(fileSnapshot.exists());
        assertFalse(fileSnapshot.attributes().isPresent());
        assertFalse(fileSnapshot.content().isPresent());
        assertEquals(path.toPath(), fileSnapshot.path());
    }

}