class DummyClass_176783 {
    @Test
    public void shouldExtractTwoGroupsInPattern() throws Exception {
        List<String> groups = capturer.extractGroups("{(hello)}, {(world)}");
        assertThat(groups, contains("hello", "world"));
    }

}