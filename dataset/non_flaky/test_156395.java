class DummyClass_156395 {
    @Test
    public void testIsAnyBlank() {
        assertTrue(StringUtils.isAnyBlank((String) null));
        assertFalse(StringUtils.isAnyBlank((String[]) null));
        assertTrue(StringUtils.isAnyBlank(null, "foo"));
        assertTrue(StringUtils.isAnyBlank(null, null));
        assertTrue(StringUtils.isAnyBlank("", "bar"));
        assertTrue(StringUtils.isAnyBlank("bob", ""));
        assertTrue(StringUtils.isAnyBlank("  bob  ", null));
        assertTrue(StringUtils.isAnyBlank(" ", "bar"));
        assertFalse(StringUtils.isAnyBlank("foo", "bar"));
    }

}