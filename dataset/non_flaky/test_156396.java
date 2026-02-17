class DummyClass_156396 {
    @Test
    public void testIsNoneBlank() {
        assertFalse(StringUtils.isNoneBlank((String) null));
        assertTrue(StringUtils.isNoneBlank((String[]) null));
        assertFalse(StringUtils.isNoneBlank(null, "foo"));
        assertFalse(StringUtils.isNoneBlank(null, null));
        assertFalse(StringUtils.isNoneBlank("", "bar"));
        assertFalse(StringUtils.isNoneBlank("bob", ""));
        assertFalse(StringUtils.isNoneBlank("  bob  ", null));
        assertFalse(StringUtils.isNoneBlank(" ", "bar"));
        assertTrue(StringUtils.isNoneBlank("foo", "bar"));
    }

}