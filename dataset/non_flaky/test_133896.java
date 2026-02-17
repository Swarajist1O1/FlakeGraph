class DummyClass_133896 {
    @Test
    public void should_identify_language_based_on_alternative_name() throws IllegalLanguageException {
        assertThat(Language.of("js"), is(JAVASCRIPT));
        assertThat(Language.of("C#"), is(CSHARP));
    }

}