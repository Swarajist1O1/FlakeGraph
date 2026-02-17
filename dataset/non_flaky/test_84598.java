class DummyClass_84598 {
    @Test
    public void testBuffer() {
        final byte[] expected = "hello-world".getBytes(StandardCharsets.UTF_8);
        final String tag = "tag1";
        checkWriterAndReader(
                (oa) -> oa.writeBuffer(expected, tag),
                (ia) -> {
                    byte[] actual = ia.readBuffer(tag);
                    assertArrayEquals(expected, actual);
                }
        );
    }

}