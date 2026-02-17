class DummyClass_122616 {
    @Test
    public void exists() {
        UnixPath unixPath = mock(UnixPath.class);
        FileAttributesCache cache = new FileAttributesCache(unixPath);

        when(unixPath.getAttributesIfExists()).thenReturn(Optional.empty());
        assertFalse(cache.get().isPresent());
        verify(unixPath, times(1)).getAttributesIfExists();
        verifyNoMoreInteractions(unixPath);

        FileAttributes attributes = mock(FileAttributes.class);
        when(unixPath.getAttributesIfExists()).thenReturn(Optional.of(attributes));
        assertTrue(cache.get().isPresent());
        verify(unixPath, times(1 + 1)).getAttributesIfExists();
        verifyNoMoreInteractions(unixPath);

        assertEquals(attributes, cache.getOrThrow());
        verifyNoMoreInteractions(unixPath);
    }

}