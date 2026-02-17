class DummyClass_118774 {
    @Test
    public void testUtf8BytesWithSurrogates() {
        final String s = "a\uD800\uDC00b";
        checkUtf8Bytes(s);
    }

}