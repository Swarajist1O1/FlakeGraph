class DummyClass_86083 {
    @Test
    public void update() {
        final String newTitle = "A NEW TITLE " + DateTime.now(DateTimeZone.UTC).toString();
        final String newDescription = "A NEW DESCRIPTION " + DateTime.now(DateTimeZone.UTC).toString();

        final EventDefinitionDto existingDto = eventDefinitionService.get("54e3deadbeefdeadbeef0000").orElse(null);
        final JobDefinitionDto existingJobDefinition = jobDefinitionService.get("54e3deadbeefdeadbeef0001").orElse(null);
        final JobTriggerDto existingTrigger = jobTriggerService.get("54e3deadbeefdeadbeef0002").orElse(null);
        final TestEventProcessorConfig existingConfig = (TestEventProcessorConfig) existingDto.config();
        final TestEventProcessorConfig newConfig = existingConfig.toBuilder()
                .executeEveryMs(550000)
                .searchWithinMs(800000)
                .build();
        final EventProcessorExecutionJob.Data existingTriggerData = (EventProcessorExecutionJob.Data) existingTrigger.data().orElseThrow(AssertionError::new);

        assertThat(existingDto).isNotNull();
        assertThat(existingJobDefinition).isNotNull();
        assertThat(existingTrigger).isNotNull();

        final EventDefinitionDto updatedDto = existingDto.toBuilder()
                .title(newTitle)
                .description(newDescription)
                .config(newConfig)
                .build();

        assertThat(handler.update(updatedDto, true)).isNotEqualTo(existingDto);

        assertThat(eventDefinitionService.get(existingDto.id())).isPresent().get().satisfies(dto -> {
            assertThat(dto.id()).isEqualTo(existingDto.id());
            assertThat(dto.title()).isEqualTo(newTitle);
            assertThat(dto.description()).isEqualTo(newDescription);
        });

        // Test that the schedule is updated to the new config
        final JobDefinitionDto newJobDefinition = jobDefinitionService.get("54e3deadbeefdeadbeef0001").orElseThrow(AssertionError::new);
        assertThat(newJobDefinition.title()).isEqualTo(newTitle);
        assertThat(newJobDefinition.description()).isEqualTo(newDescription);
        assertThat(((EventProcessorExecutionJob.Config) newJobDefinition.config()).processingHopSize()).isEqualTo(550000);
        assertThat(((EventProcessorExecutionJob.Config) newJobDefinition.config()).processingWindowSize()).isEqualTo(800000);

        // Test if the EventDefinition update removed the old trigger data
        // and reset the job definition timerange to the new parameters
        final EventProcessorExecutionJob.Config newJobConfig = (EventProcessorExecutionJob.Config) newJobDefinition.config();
        final TimeRange newTimeRange = newJobConfig.parameters().timerange();
        assertThat(newTimeRange.getFrom()).isEqualTo(clock.nowUTC().minus(newConfig.searchWithinMs()));
        assertThat(newTimeRange.getTo()).isEqualTo(clock.nowUTC());

        assertThat(jobTriggerService.get("54e3deadbeefdeadbeef0002")).isPresent().get().satisfies(trigger -> {
            assertThat(trigger.data()).isEmpty();
            assertThat(trigger.nextTime()).isEqualTo(clock.nowUTC());
        });
    }

}