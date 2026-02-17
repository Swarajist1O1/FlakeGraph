class DummyClass_38233 {
    @Test
    public void testEscapeHtmlTwoByteUnicode() {
        assertTrue("&#192;".equals(TextUtils.escapeHtml(u00C0)));
        assertTrue("&#256;".equals(TextUtils.escapeHtml(u0100)));
        assertTrue("&#288;".equals(TextUtils.escapeHtml(u0120)));
    }

}