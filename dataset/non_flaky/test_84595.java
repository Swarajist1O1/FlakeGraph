class DummyClass_84595 {
    @Test
    public void testString() {
        final String expected = "hello";
        final String tag = "tag1";
        checkWriterAndReader(
                (oa) -> oa.writeString(expected, tag),
                (ia) -> {
                    String actual = ia.readString(tag);
                    assertEquals(expected, actual);
                }
        );
    }

}