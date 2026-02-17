class DummyClass_86061 {
    @Test
    public void testValidateWithInvalidExecutionTime() {
        final AggregationEventProcessorConfig invalidConfig1 = getConfig().toBuilder()
            .executeEveryMs(-1)
            .build();

        final ValidationResult validationResult1 = invalidConfig1.validate();
        assertThat(validationResult1.failed()).isTrue();
        assertThat(validationResult1.getErrors()).containsOnlyKeys("execute_every_ms");

        final AggregationEventProcessorConfig invalidConfig2 = invalidConfig1.toBuilder()
            .executeEveryMs(0)
            .build();

        final ValidationResult validationResult2 = invalidConfig2.validate();
        assertThat(validationResult2.failed()).isTrue();
        assertThat(validationResult2.getErrors()).containsOnlyKeys("execute_every_ms");
    }

}