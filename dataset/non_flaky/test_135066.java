class DummyClass_135066 {
    @Test
    public void testIsAbsoluteUrlRecognizingRelativeUrls() {
        assertFalse(URLUtils.isAbsoluteUrl("relative"));
        assertFalse(URLUtils.isAbsoluteUrl("relative/path"));
        assertFalse(URLUtils.isAbsoluteUrl("relative/path?query=val"));
        assertFalse(URLUtils.isAbsoluteUrl("/root/relative/path"));
    }

}