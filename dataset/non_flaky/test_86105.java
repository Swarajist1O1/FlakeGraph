class DummyClass_86105 {
    @Test
    public void findByEventProcessorsAndMaxTimestamp() {
        assertThat(stateService.findByEventDefinitionId("54e3deadbeefdeadbeefaff3")).isPresent().get().satisfies(dto -> {
            final DateTime maxTs = dto.maxProcessedTimestamp();
            final String id = dto.eventDefinitionId();

            assertThat(stateService.findByEventDefinitionsAndMaxTimestamp(ImmutableSet.of(id), maxTs))
                    .hasSize(1);
            assertThat(stateService.findByEventDefinitionsAndMaxTimestamp(ImmutableSet.of(id), maxTs.minusHours(1)))
                    .hasSize(1);
            assertThat(stateService.findByEventDefinitionsAndMaxTimestamp(ImmutableSet.of(id), maxTs.plusHours(1)))
                    .hasSize(0);

            assertThatThrownBy(() -> stateService.findByEventDefinitionsAndMaxTimestamp(ImmutableSet.of(), maxTs))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> stateService.findByEventDefinitionsAndMaxTimestamp(null, maxTs))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> stateService.findByEventDefinitionsAndMaxTimestamp(ImmutableSet.of(id), null))
                    .isInstanceOf(IllegalArgumentException.class);

            assertThat(stateService.findByEventDefinitionsAndMaxTimestamp(ImmutableSet.of("nope"), maxTs))
                    .hasSize(0);
            assertThat(stateService.findByEventDefinitionsAndMaxTimestamp(ImmutableSet.of(id, "nope"), maxTs))
                    .hasSize(1);
        });
    }

}