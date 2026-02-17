class DummyClass_176786 {
    @Test
    public void shouldExtractDotSeparatedName() throws Exception {
        List<String> groups = capturer.extractGroups("{(hello.world)}");
        assertThat(groups, contains("hello.world"));
    }

}