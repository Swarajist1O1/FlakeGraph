class DummyClass_38218 {
    @Test
    public void testPluralization() throws Exception {
        assertEquals("", TextUtils.pluralize(null));
        assertEquals("", TextUtils.pluralize(""));
        assertEquals("dogs", TextUtils.pluralize("dog"));
        assertEquals("keywords", TextUtils.pluralize("keywords"));
    }

}