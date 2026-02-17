class DummyClass_86064 {
    @Test
    public void testValidFilterConfiguration() {
        final AggregationEventProcessorConfig config = getConfig().toBuilder()
            .query("foo")
            .streams(ImmutableSet.of("1", "2"))
            .build();

        final ValidationResult validationResult = config.validate();
        assertThat(validationResult.failed()).isFalse();
        assertThat(validationResult.getErrors().size()).isEqualTo(0);
    }

}