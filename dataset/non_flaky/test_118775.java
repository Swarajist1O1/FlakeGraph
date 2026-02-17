class DummyClass_118775 {
    @Test
    public void testUtf8BytesWithNonSurrogates3Bytes() {
        final String s = "a\uE000b";
        checkUtf8Bytes(s);
    }

}