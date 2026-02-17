class DummyClass_38232 {
    @Test
    public void testEscapeHtmlWhitespaceHandling() {
        String input2 = "a b  c   d    e";
        String output2 = "a b &nbsp;c &nbsp; d &nbsp; &nbsp;e";
        assertTrue(output2.equals(TextUtils.escapeHtml(input2)));

        String input3 = "line 1\nline 2 \n\n line4";
        String output3f = "line 1<br/>line 2 <br/><br/> line4";
        String output3t = "line 1line 2  line4";
        assertTrue(output3f.equals(TextUtils.escapeHtml(input3, false)));
        assertTrue(output3t.equals(TextUtils.escapeHtml(input3, true)));
    }

}