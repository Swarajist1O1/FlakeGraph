class DummyClass_135068 {
    @Test
    public void testIsAbsoluteUrlIgnoresSyntaxErrorsAreNotAbsolute() {
        assertFalse(URLUtils.isAbsoluteUrl(":"));
    }

}