class DummyClass_176788 {
    @Test
    public void testInflateGroups_emptyWorld() throws Exception {
        String value = inflater.inflateGroups("{(foo)} bar", Sets.newHashSet("foo"));
        assertThat(value, equalTo("{(foo)} bar"));
    }

}