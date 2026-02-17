class DummyClass_86084 {
    @Test
    public void updateWithSchedulingDisabled() {
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

        assertThat(existingDto).isNotNull();
        assertThat(existingJobDefinition).isNotNull();
        assertThat(existingTrigger).isNotNull();

        final EventDefinitionDto updatedDto = existingDto.toBuilder()
                .title(newTitle)
                .description(newDescription)
                .config(newConfig)
                .build();

        assertThat(handler.update(updatedDto, false)).isNotEqualTo(existingDto);

        assertThat(eventDefinitionService.get(existingDto.id())).isPresent().get().satisfies(dto -> {
            assertThat(dto.id()).isEqualTo(existingDto.id());
            assertThat(dto.title()).isEqualTo(newTitle);
            assertThat(dto.description()).isEqualTo(newDescription);
        });

        assertThat(jobDefinitionService.get("54e3deadbeefdeadbeef0001")).isNotPresent();
        assertThat(jobTriggerService.get("54e3deadbeefdeadbeef0002")).isNotPresent();
    }

}