class DummyClass_176779 {
    @Test
    public void byPattern1() throws Exception {
        assertThat(generator.byPattern("A"), ContainsPattern.matchesPattern("[A-Z]"));
        assertThat(generator.byPattern("a"), ContainsPattern.matchesPattern("[a-z]"));
        assertThat(generator.byPattern("0"), ContainsPattern.matchesPattern("[0-9]"));

        assertThat(generator.byPattern("0Aa"), ContainsPattern.matchesPattern(Pattern.compile("[0-9][A-Z][a-z]")));
    }

}