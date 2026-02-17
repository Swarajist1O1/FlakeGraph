class DummyClass_38228 {
    @Test
    public void testcleanUTF8String() throws Exception {
        String cleanString = "Hello World";
        String dirtyString = "Hello\u0007World";

        String cleanedString = TextUtils.cleanUTF8String(dirtyString);
        assertEquals(cleanString, cleanedString);
        assertEquals(cleanString, TextUtils.cleanUTF8String(cleanString));
    }

}