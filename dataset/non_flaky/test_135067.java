class DummyClass_135067 {
    @Test
    public void testIsAbsoluteUrlRecognizingEmptyOrNullAsRelative() {
        assertFalse(URLUtils.isAbsoluteUrl(null));
        assertFalse(URLUtils.isAbsoluteUrl(""));
    }

}