class DummyClass_86103 {
    @Test
    public void loading() {
        final Optional<EventProcessorStateDto> stateDto = stateService.findByEventDefinitionId("54e3deadbeefdeadbeefaff3");

        assertThat(stateDto).isPresent().get().satisfies(dto -> {
            assertThat(dto.id()).isEqualTo("54e3deadbeefdeadbeefaffe");
            assertThat(dto.eventDefinitionId()).isEqualTo("54e3deadbeefdeadbeefaff3");
            assertThat(dto.minProcessedTimestamp()).isEqualTo(DateTime.parse("2019-01-01T00:00:00.000Z"));
            assertThat(dto.maxProcessedTimestamp()).isEqualTo(DateTime.parse("2019-01-01T01:00:00.000Z"));
        });
    }

}