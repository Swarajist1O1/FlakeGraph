class DummyClass_156399 {
    @Test
    public void testFirstNonEmpty() {
        assertNull(StringUtils.firstNonEmpty());
        assertNull(StringUtils.firstNonEmpty((String[]) null));
        assertNull(StringUtils.firstNonEmpty(null, null, null));
        assertEquals(" ", StringUtils.firstNonEmpty(null, "", " "));
        assertNull(StringUtils.firstNonEmpty(null, null, ""));
        assertEquals("zz", StringUtils.firstNonEmpty(null, "zz"));
        assertEquals("abc", StringUtils.firstNonEmpty("abc"));
        assertEquals("xyz", StringUtils.firstNonEmpty(null, "xyz"));
        assertEquals("xyz", StringUtils.firstNonEmpty(null, "xyz", "abc"));
    }

}