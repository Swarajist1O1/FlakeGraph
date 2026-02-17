class DummyClass_135065 {
    @Test
    public void testIsAbsoluteUrlRecognizingAppUrls() {
        assertTrue(URLUtils.isAbsoluteUrl("com.example.app:/oauth2redirect/example-provider"));
        assertTrue(URLUtils.isAbsoluteUrl("com.example.app:/oauth2redirect/example-provider?query=val"));
    }

}