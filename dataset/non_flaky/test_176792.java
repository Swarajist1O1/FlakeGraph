class DummyClass_176792 {
    @Test
    public void testInflateGroups_multipleExist() throws Exception {
        doReturn(Optional.of("foo")).when(world).get("foo");
        doReturn(Optional.of("bar")).when(world).get("bar");
        String value = inflater.inflateGroups("{(foo)} {(bar)}", Sets.newHashSet("foo", "bar"));
        assertThat(value, equalTo("foo bar"));
    }

}