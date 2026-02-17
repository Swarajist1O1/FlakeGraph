class DummyClass_176784 {
    @Test
    public void shouldNotExtractGroupsInPatternWithSpacesInName() throws Exception {
        List<String> groups = capturer.extractGroups("{(hello world)}");
        assertThat(groups, is(empty()));
    }

}