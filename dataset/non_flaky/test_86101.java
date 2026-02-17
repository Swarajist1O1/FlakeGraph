class DummyClass_86101 {
    @Test
    public void save() {
        final EventDefinitionDto newDto = EventDefinitionDto.builder()
                .title("Test")
                .description("A test event definition")
                .config(TestEventProcessorConfig.builder()
                        .message("This is a test event processor")
                        .searchWithinMs(1000)
                        .executeEveryMs(1000)
                        .build())
                .priority(3)
                .alert(false)
                .notificationSettings(EventNotificationSettings.withGracePeriod(60000))
                .keySpec(ImmutableList.of("a", "b"))
                .notifications(ImmutableList.of())
                .build();

        final EventDefinitionDto dto = dbService.save(newDto);

        assertThat(dto.id()).isNotBlank();
        assertThat(dto.title()).isEqualTo("Test");
        assertThat(dto.description()).isEqualTo("A test event definition");
        assertThat(dto.priority()).isEqualTo(3);
        assertThat(dto.keySpec()).isEqualTo(ImmutableList.of("a", "b"));
        assertThat(dto.fieldSpec()).isEmpty();
        assertThat(dto.notifications()).isEmpty();
        assertThat(dto.storage()).hasSize(1);
        // We will always add a persist-to-streams handler for now
        assertThat(dto.storage()).containsOnly(PersistToStreamsStorageHandler.Config.createWithDefaultEventsStream());
    }

}