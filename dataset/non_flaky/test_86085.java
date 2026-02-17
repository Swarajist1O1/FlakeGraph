class DummyClass_86085 {
    @Test
    public void updateWithSchedulingReEnabled() {
        final String newTitle = "A NEW TITLE " + DateTime.now(DateTimeZone.UTC).toString();
        final String newDescription = "A NEW DESCRIPTION " + DateTime.now(DateTimeZone.UTC).toString();

        final EventDefinitionDto existingDto = eventDefinitionService.get("54e3deadbeefdeadbeef0000").orElse(null);
        final TestEventProcessorConfig existingConfig = (TestEventProcessorConfig) existingDto.config();
        final TestEventProcessorConfig newConfig = existingConfig.toBuilder()
                .executeEveryMs(550000)
                .searchWithinMs(800000)
                .build();

        assertThat(existingDto).isNotNull();

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

        final JobDefinitionDto newJobDefinition = jobDefinitionService.getByConfigField("event_definition_id", existingDto.id())
                .orElseThrow(AssertionError::new);
        assertThat(newJobDefinition.title()).isEqualTo(newTitle);
        assertThat(newJobDefinition.description()).isEqualTo(newDescription);
        assertThat(((EventProcessorExecutionJob.Config) newJobDefinition.config()).processingHopSize()).isEqualTo(550000);

        assertThat(jobTriggerService.getForJob(newJobDefinition.id()).get(0)).satisfies(trigger -> {
            final IntervalJobSchedule schedule = (IntervalJobSchedule) trigger.schedule();
            assertThat(schedule.interval()).isEqualTo(550000);
        });
    }

}