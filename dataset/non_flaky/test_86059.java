class DummyClass_86059 {
    @Test
    public void toJobSchedulerConfig() {
        final EventDefinitionDto dto = dbService.get("54e3deadbeefdeadbeefaffe").orElse(null);

        assertThat(dto).isNotNull();

        assertThat(dto.config().toJobSchedulerConfig(dto, clock)).isPresent().get().satisfies(schedulerConfig -> {
            assertThat(schedulerConfig.jobDefinitionConfig()).satisfies(jobDefinitionConfig -> {
                assertThat(jobDefinitionConfig).isInstanceOf(EventProcessorExecutionJob.Config.class);

                final EventProcessorExecutionJob.Config config = (EventProcessorExecutionJob.Config) jobDefinitionConfig;

                assertThat(config.eventDefinitionId()).isEqualTo(dto.id());
                assertThat(config.processingWindowSize()).isEqualTo(300000);
                assertThat(config.processingHopSize()).isEqualTo(300000);
                assertThat(config.parameters()).isEqualTo(AggregationEventProcessorParameters.builder()
                        .timerange(AbsoluteRange.create(clock.nowUTC().minus(300000), clock.nowUTC()))
                        .build());
            });

            assertThat(schedulerConfig.schedule()).satisfies(schedule -> {
                assertThat(schedule).isInstanceOf(IntervalJobSchedule.class);

                final IntervalJobSchedule config = (IntervalJobSchedule) schedule;

                assertThat(config.interval()).isEqualTo(300000);
                assertThat(config.unit()).isEqualTo(TimeUnit.MILLISECONDS);
            });
        });
    }

}