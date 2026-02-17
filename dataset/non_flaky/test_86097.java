class DummyClass_86097 {
    @Test
    public void testValidEventDefinition() {
        final ValidationResult validationResult = testSubject.validate();
        assertThat(validationResult.failed()).isFalse();
        assertThat(validationResult.getErrors().size()).isEqualTo(0);
    }

}