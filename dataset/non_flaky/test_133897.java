class DummyClass_133897 {
    @Test
    public void should_ignore_spaces_and_capitalisation_when_matching() throws IllegalLanguageException {
        assertThat(Language.of("  JaVa \n  "), is(JAVA));
    }

}