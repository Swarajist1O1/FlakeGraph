class DummyClass_176781 {
    @Test
    public void shouldExtractNoGroupsInPattern() throws Exception {
        List<String> groups = capturer.extractGroups("(hello)");
        assertThat(groups, is(empty()));
    }

}