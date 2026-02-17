class DummyClass_122604 {
    @Test
    public void updateWith() {
        cache.updateWith(content, Instant.ofEpochMilli(2));
        assertArrayEquals(content, cache.get(Instant.ofEpochMilli(2)));
        verifyNoMoreInteractions(unixPath);

        cache.updateWith(newContent, Instant.ofEpochMilli(4));
        assertArrayEquals(newContent, cache.get(Instant.ofEpochMilli(4)));
        verifyNoMoreInteractions(unixPath);
    }

}