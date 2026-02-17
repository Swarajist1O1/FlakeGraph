class DummyClass_176789 {
    @Test
    public void testInflateGroups_multipleEmpty() throws Exception {
        String value = inflater.inflateGroups("{(foo)} {(bar)}", Sets.newHashSet("foo", "bar"));
        assertThat(value, equalTo("{(foo)} {(bar)}"));
    }

}