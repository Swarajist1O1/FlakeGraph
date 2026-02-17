class DummyClass_156398 {
    @Test
    public void testFirstNonBlank() {
        assertNull(StringUtils.firstNonBlank());
        assertNull(StringUtils.firstNonBlank((String[]) null));
        assertNull(StringUtils.firstNonBlank(null, null, null));
        assertNull(StringUtils.firstNonBlank(null, "", " "));
        assertNull(StringUtils.firstNonBlank(null, null, " "));
        assertEquals("zz", StringUtils.firstNonBlank(null, "zz"));
        assertEquals("abc", StringUtils.firstNonBlank("abc"));
        assertEquals("xyz", StringUtils.firstNonBlank(null, "xyz"));
        assertEquals("xyz", StringUtils.firstNonBlank(null, "xyz", "abc"));
    }

}