class DummyClass_38221 {
    @Test
    public void testTruncateStringToCharLength() {
        String string = "abcde";
        assertEquals(string, TextUtils.truncateStringToCharLength(string, 5, "..."));
        assertEquals(string, TextUtils.truncateStringToCharLength(string, 5, ""));
        assertEquals(string, TextUtils.truncateStringToCharLength(string, 6, "..."));
        assertEquals(string, TextUtils.truncateStringToCharLength(string, 6, ""));
        assertEquals("a...", TextUtils.truncateStringToCharLength(string, 4, "..."));
        assertEquals("abcd", TextUtils.truncateStringToCharLength(string, 4, ""));
    }

}