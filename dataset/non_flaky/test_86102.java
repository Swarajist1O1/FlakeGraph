class DummyClass_86102 {
    @Test
    public void persistence() {
        final DateTime now = DateTime.now(DateTimeZone.UTC);
        final DateTime min = now.minusHours(1);
        final DateTime max = now;

        final EventProcessorStateDto stateDto = EventProcessorStateDto.builder()
                .eventDefinitionId("abc123")
                .minProcessedTimestamp(min)
                .maxProcessedTimestamp(max)
                .build();

        assertThat(stateService.setState(stateDto)).isPresent().get().satisfies(dto -> {
            assertThat(dto.id()).isNotBlank();
            assertThat(dto.eventDefinitionId()).isEqualTo("abc123");
            assertThat(dto.minProcessedTimestamp()).isEqualTo(min);
            assertThat(dto.maxProcessedTimestamp()).isEqualTo(max);
        });

        assertThatThrownBy(() -> stateService.setState("", min, max))
                .hasMessageContaining("eventDefinitionId")
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> stateService.setState(null, min, max))
                .hasMessageContaining("eventDefinitionId")
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> stateService.setState("a", null, max))
                .hasMessageContaining("minProcessedTimestamp")
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> stateService.setState("a", min, null))
                .hasMessageContaining("maxProcessedTimestamp")
                .isInstanceOf(IllegalArgumentException.class);

        // A max timestamp that is older than the min timestamp is an error! (e.g. mixing up arguments)
        assertThatThrownBy(() -> stateService.setState("a", max, min))
                .hasMessageContaining("minProcessedTimestamp")
                .hasMessageContaining("maxProcessedTimestamp")
                .isInstanceOf(IllegalArgumentException.class);
    }

}