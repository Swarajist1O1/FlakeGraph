class DummyClass_122603 {
    @Test
    public void get() {
        when(unixPath.readBytes()).thenReturn(content);
        assertArrayEquals(content, cache.get(Instant.ofEpochMilli(0)));
        verify(unixPath, times(1)).readBytes();
        verifyNoMoreInteractions(unixPath);

        // cache hit
        assertArrayEquals(content, cache.get(Instant.ofEpochMilli(0)));
        verify(unixPath, times(1)).readBytes();
        verifyNoMoreInteractions(unixPath);

        // cache miss
        when(unixPath.readBytes()).thenReturn(newContent);
        assertArrayEquals(newContent, cache.get(Instant.ofEpochMilli(1)));
        verify(unixPath, times(1 + 1)).readBytes();
        verifyNoMoreInteractions(unixPath);

        // cache hit both at times 0 and 1
        assertArrayEquals(newContent, cache.get(Instant.ofEpochMilli(0)));
        verify(unixPath, times(1 + 1)).readBytes();
        verifyNoMoreInteractions(unixPath);
        assertArrayEquals(newContent, cache.get(Instant.ofEpochMilli(1)));
        verify(unixPath, times(1 + 1)).readBytes();
        verifyNoMoreInteractions(unixPath);
    }

}