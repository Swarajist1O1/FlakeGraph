class DummyClass_176791 {
    @Test
    public void testInflateGroups_withPlainText() throws Exception {
        doReturn(Optional.of("foo")).when(world).get("foo");
        String value = inflater.inflateGroups("my {(foo)} is very {(bar)} !", Sets.newHashSet("foo", "bar"));
        assertThat(value, equalTo("my foo is very {(bar)} !"));
    }

}