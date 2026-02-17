class DummyClass_176780 {
    @Test
    public void withLength() throws Exception {
        assertThat(generator.withLength(5), ContainsPattern.matchesPattern(Pattern.compile("[A-Za-z0-9]{5}")));
    }

}