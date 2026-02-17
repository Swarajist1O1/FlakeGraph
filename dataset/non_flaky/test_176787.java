class DummyClass_176787 {
    @Test
    public void testInflateGroups() throws Exception {
        doReturn(Optional.of("foo")).when(world).get("foo");
        String value = inflater.inflateGroups("{(foo)} bar", Sets.newHashSet("foo"));
        assertThat(value, equalTo("foo bar"));
    }

}