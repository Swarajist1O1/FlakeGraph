class DummyClass_86060 {
    @Test
    public void testValidateWithInvalidTimeRange() {
        final AggregationEventProcessorConfig invalidConfig1 = getConfig().toBuilder()
            .searchWithinMs(-1)
            .build();

        final ValidationResult validationResult1 = invalidConfig1.validate();
        assertThat(validationResult1.failed()).isTrue();
        assertThat(validationResult1.getErrors()).containsOnlyKeys("search_within_ms");

        final AggregationEventProcessorConfig invalidConfig2 = invalidConfig1.toBuilder()
            .searchWithinMs(0)
            .build();

        final ValidationResult validationResult2 = invalidConfig2.validate();
        assertThat(validationResult2.failed()).isTrue();
        assertThat(validationResult2.getErrors()).containsOnlyKeys("search_within_ms");
    }

}