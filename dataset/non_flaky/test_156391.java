class DummyClass_156391 {
    @Test
    public void testIsNoneEmpty() {
        assertFalse(StringUtils.isNoneEmpty((String) null));
        assertTrue(StringUtils.isNoneEmpty((String[]) null));
        assertFalse(StringUtils.isNoneEmpty(null, "foo"));
        assertFalse(StringUtils.isNoneEmpty("", "bar"));
        assertFalse(StringUtils.isNoneEmpty("bob", ""));
        assertFalse(StringUtils.isNoneEmpty("  bob  ", null));
        assertTrue(StringUtils.isNoneEmpty(" ", "bar"));
        assertTrue(StringUtils.isNoneEmpty("foo", "bar"));
    }

}