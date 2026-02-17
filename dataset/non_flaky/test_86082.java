class DummyClass_86082 {
    @Test
    public void createWithoutSchedule() {
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

        final EventDefinitionDto dto = handler.createWithoutSchedule(newDto, Optional.empty());

        // Handler should create the event definition
        assertThat(eventDefinitionService.get(dto.id())).isPresent();

        // Handler should NOT create a job definition for the event definition/processor
        assertThat(jobDefinitionService.getByConfigField("event_definition_id", dto.id())).isNotPresent();

        // And the handler should also NOT create a job trigger for the created job definition
        assertThat(jobTriggerService.nextRunnableTrigger()).isNotPresent();
    }

}