class DummyClass_176794 {
    @Test
    public void testInflateGroups_multipleSameEmpty() throws Exception {
        String value = inflater.inflateGroups("{(foo)} {(foo)}", Sets.newHashSet("foo"));
        assertThat(value, equalTo("{(foo)} {(foo)}"));
    }

}