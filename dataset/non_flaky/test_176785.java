class DummyClass_176785 {
    @Test
    public void shouldExtractGroupsInPatternWithUnderscoreInName() throws Exception {
        List<String> groups = capturer.extractGroups("{(hello_world)}");
        assertThat(groups, contains("hello_world"));
    }

}