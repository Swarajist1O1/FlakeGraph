class DummyClass_86092 {
    @Test
    public void testValidateWithEmptyTitle() {
        final EventDefinitionDto invalidEventDefinition = testSubject.toBuilder()
            .title("")
            .build();
        final ValidationResult validationResult = invalidEventDefinition.validate();
        assertThat(validationResult.failed()).isTrue();
        assertThat(validationResult.getErrors()).containsOnlyKeys("title");
    }

}