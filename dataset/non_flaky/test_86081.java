class DummyClass_86081 {
    @Test
    public void create() {
        final EventDefinitionDto newDto = EventDefinitionDto.builder()
                .title("Test")
                .description("A test event definition")
                .config(TestEventProcessorConfig.builder()
                        .message("This is a test event processor")
                        .searchWithinMs(300000)
                        .executeEveryMs(60001)
                        .build())
                .priority(3)
                .alert(false)
                .notificationSettings(EventNotificationSettings.withGracePeriod(60000))
                .keySpec(ImmutableList.of("a", "b"))
                .notifications(ImmutableList.of())
                .build();

        final EventDefinitionDto dto = handler.create(newDto, Optional.empty());

        // Handler should create the event definition
        assertThat(eventDefinitionService.get(dto.id())).isPresent();

        final Optional<JobDefinitionDto> jobDefinition = jobDefinitionService.getByConfigField("event_definition_id", dto.id());

        // Handler also should create the job definition for the event definition/processor
        assertThat(jobDefinition).isPresent().get().satisfies(definition -> {
            assertThat(definition.title()).isEqualTo("Test");
            assertThat(definition.description()).isEqualTo("A test event definition");
            assertThat(definition.config()).isInstanceOf(EventProcessorExecutionJob.Config.class);

            final EventProcessorExecutionJob.Config config = (EventProcessorExecutionJob.Config) definition.config();


            assertThat(config.processingWindowSize()).isEqualTo(300000);
            assertThat(config.processingHopSize()).isEqualTo(60001);
        });

        // And the handler should also create a job trigger for the created job definition
        final Optional<JobTriggerDto> jobTrigger = jobTriggerService.nextRunnableTrigger();

        assertThat(jobTrigger).isPresent().get().satisfies(trigger -> {
            assertThat(trigger.jobDefinitionId()).isEqualTo(jobDefinition.get().id());
            assertThat(trigger.schedule()).isInstanceOf(IntervalJobSchedule.class);

            final IntervalJobSchedule schedule = (IntervalJobSchedule) trigger.schedule();

            assertThat(schedule.interval()).isEqualTo(60001);
            assertThat(schedule.unit()).isEqualTo(TimeUnit.MILLISECONDS);
        });
    }

}