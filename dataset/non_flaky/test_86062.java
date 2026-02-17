class DummyClass_86062 {
    @Test
    public void testValidateWithIncompleteAggregationOptions() {
        AggregationEventProcessorConfig invalidConfig = getConfig().toBuilder()
            .groupBy(ImmutableList.of("foo"))
            .build();

        ValidationResult validationResult = invalidConfig.validate();
        assertThat(validationResult.failed()).isTrue();
        assertThat(validationResult.getErrors()).containsOnlyKeys("series", "conditions");

        invalidConfig = getConfig().toBuilder()
            .series(ImmutableList.of(this.getSeries()))
            .build();

        validationResult = invalidConfig.validate();
        assertThat(validationResult.failed()).isTrue();
        assertThat(validationResult.getErrors()).containsOnlyKeys("conditions");

        invalidConfig = getConfig().toBuilder()
            .conditions(this.getConditions())
            .build();

        validationResult = invalidConfig.validate();
        assertThat(validationResult.failed()).isTrue();
        assertThat(validationResult.getErrors()).containsOnlyKeys("series");
    }

}