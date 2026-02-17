class DummyClass_133898 {
    @Test
    public void should_throw_exception_if_language_not_recognised() throws IllegalLanguageException {
        thrown.expect(IllegalLanguageException.class);
        Language.of("none");
    }

}