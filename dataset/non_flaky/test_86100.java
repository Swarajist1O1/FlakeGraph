class DummyClass_86100 {
    @Test
    public void loadPersisted() {
        final List<EventDefinitionDto> dtos = dbService.streamAll().collect(Collectors.toList());

        assertThat(dtos).hasSize(1);

        assertThat(dtos.get(0)).satisfies(dto -> {
            assertThat(dto.id()).isNotBlank();
            assertThat(dto.title()).isEqualTo("Test");
            assertThat(dto.description()).isEqualTo("A test event definition");
            assertThat(dto.priority()).isEqualTo(2);
            assertThat(dto.keySpec()).isEqualTo(ImmutableList.of("username"));
            assertThat(dto.fieldSpec()).isEmpty();
            assertThat(dto.notifications()).isEmpty();
            assertThat(dto.storage()).hasSize(1);

            assertThat(dto.config()).isInstanceOf(TestEventProcessorConfig.class);
            assertThat(dto.config()).satisfies(abstractConfig -> {
                final TestEventProcessorConfig config = (TestEventProcessorConfig) abstractConfig;

                assertThat(config.type()).isEqualTo("__test_event_processor_config__");
                assertThat(config.message()).isEqualTo("This is a test event processor");
            });
        });
    }

}