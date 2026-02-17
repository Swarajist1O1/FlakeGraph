class DummyClass_135064 {
    @Test
    public void testIsAbsoluteUrlRecognizingAbsolutUrls() {
        assertTrue(URLUtils.isAbsoluteUrl("https://some.valid.url:8080/path?query=val"));
        assertTrue(URLUtils.isAbsoluteUrl("http://some.valid.url:8080/path?query=val"));
        assertTrue(URLUtils.isAbsoluteUrl("http://some.valid.url"));
    }

}