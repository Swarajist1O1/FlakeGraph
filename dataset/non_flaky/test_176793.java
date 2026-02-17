class DummyClass_176793 {
    @Test
    public void testInflateGroups_multipleSameExist() throws Exception {
        doReturn(Optional.of("foo")).when(world).get("foo");
        String value = inflater.inflateGroups("{(foo)} {(foo)}", Sets.newHashSet("foo"));
        assertThat(value, equalTo("foo foo"));
    }

}