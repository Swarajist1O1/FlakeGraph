class DummyClass_86093 {
    @Test
    public void testValidateWithEmptyConfigType() {
        final EventDefinitionDto invalidEventDefinition = testSubject.toBuilder()
            .config(new EventProcessorConfig.FallbackConfig())
            .build();
        final ValidationResult validationResult = invalidEventDefinition.validate();
        assertThat(validationResult.failed()).isTrue();
        assertThat(validationResult.getErrors()).containsOnlyKeys("config");
    }

}