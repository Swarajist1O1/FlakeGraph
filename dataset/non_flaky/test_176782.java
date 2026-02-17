class DummyClass_176782 {
    @Test
    public void shouldExtractSingleGroupInPattern() throws Exception {
        List<String> groups = capturer.extractGroups("{(hello)}");
        assertThat(groups, contains("hello"));
    }

}