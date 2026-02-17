class DummyClass_133895 {
    @Test
    public void should_identify_language_based_on_key() throws IllegalLanguageException {
        assertThat(Language.of("java"), is(JAVA));
    }

}