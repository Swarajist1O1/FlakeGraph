class DummyClass_38222 {
    @Test
    public void testTruncateLabelString() {
        // TODO(nackner): Add in more tests with UTF-8 characters, but they won't play nice with the
        // build or people's Eclipse clients even if commented out.
        String string = "abcde";
        assertEquals(string, TextUtils.truncateLabelString(string, 5));
        assertEquals(string, TextUtils.truncateLabelString(string, 6, "..."));
        assertEquals("a...", TextUtils.truncateLabelString(string, 4, "..."));
    }

}