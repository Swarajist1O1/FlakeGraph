class DummyClass_176790 {
    @Test
    public void testInflateGroups_halfEmpty() throws Exception {
        doReturn(Optional.of("foo")).when(world).get("foo");
        String value = inflater.inflateGroups("{(foo)} {(bar)}", Sets.newHashSet("foo", "bar"));
        assertThat(value, equalTo("foo {(bar)}"));
    }

}