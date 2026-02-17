class DummyClass_86065 {
    @Test
    public void testValidAggregationConfiguration() {
        final AggregationEventProcessorConfig config = getConfig().toBuilder()
            .groupBy(ImmutableList.of("bar"))
            .series(ImmutableList.of(this.getSeries()))
            .conditions(this.getConditions())
            .build();

        final ValidationResult validationResult = config.validate();
        assertThat(validationResult.failed()).isFalse();
        assertThat(validationResult.getErrors().size()).isEqualTo(0);
    }

}