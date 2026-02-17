class DummyClass_86094 {
    @Test
    public void testValidateWithInvalidConfig() {
        final AggregationEventProcessorConfig configMock = mock(AggregationEventProcessorConfig.class);
        final ValidationResult mockedValidationResult = new ValidationResult();
        mockedValidationResult.addError("foo", "bar");
        when(configMock.validate()).thenReturn(mockedValidationResult);

        final EventDefinitionDto invalidEventDefinition = testSubject.toBuilder()
            .config(configMock)
            .build();
        final ValidationResult validationResult = invalidEventDefinition.validate();
        assertThat(validationResult.failed()).isTrue();
        assertThat(validationResult.getErrors()).containsOnlyKeys("foo");
    }

}