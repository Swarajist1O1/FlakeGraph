class DummyClass_156397 {
    @Test
    public void testIsAllBlank() {
        assertTrue(StringUtils.isAllBlank((String) null));
        assertTrue(StringUtils.isAllBlank((String[]) null));
        assertTrue(StringUtils.isAllBlank(null, null));
        assertTrue(StringUtils.isAllBlank(null, " "));
        assertFalse(StringUtils.isAllBlank(null, "foo"));
        assertFalse(StringUtils.isAllBlank("", "bar"));
        assertFalse(StringUtils.isAllBlank("bob", ""));
        assertFalse(StringUtils.isAllBlank("  bob  ", null));
        assertFalse(StringUtils.isAllBlank(" ", "bar"));
        assertFalse(StringUtils.isAllBlank("foo", "bar"));
    }

}