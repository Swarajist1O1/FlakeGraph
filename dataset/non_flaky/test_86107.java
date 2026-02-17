class DummyClass_86107 {
    @Test
    public void setStateKeepsMinMaxTimestamp() {
        final DateTime now = DateTime.now(DateTimeZone.UTC);
        final DateTime min = now.minusHours(1);
        final DateTime max = now;

        // Before we set the state, there should be no record
        assertThat(stateService.findByEventDefinitionId("yolo")).isNotPresent();

        // Create state
        stateService.setState("yolo", min, now);

        // Check that it has been created
        assertThat(stateService.findByEventDefinitionId("yolo"))
                .isPresent()
                .get()
                .satisfies(dto -> {
                    assertThat(dto.minProcessedTimestamp()).isEqualTo(min);
                    assertThat(dto.maxProcessedTimestamp()).isEqualTo(now);
                });

        // Overwrite state with an EARLIER max timestamp
        stateService.setState("yolo", min, max.minusMinutes(10));

        // Max timestamp should NOT be overwritten by older timestamp
        assertThat(stateService.findByEventDefinitionId("yolo"))
                .isPresent()
                .get()
                .satisfies(dto -> {
                    assertThat(dto.minProcessedTimestamp()).isEqualTo(min);
                    assertThat(dto.maxProcessedTimestamp()).isEqualTo(max);
                });

        // Overwrite state with a LATER min timestamp
        stateService.setState("yolo", min.plusMinutes(5), max);

        // Min timestamp should NOT be overwritten by younger timestamp
        assertThat(stateService.findByEventDefinitionId("yolo"))
                .isPresent()
                .get()
                .satisfies(dto -> {
                    assertThat(dto.minProcessedTimestamp()).isEqualTo(min);
                    assertThat(dto.maxProcessedTimestamp()).isEqualTo(max);
                });

        // Overwrite state with a NEWER max timestamp
        stateService.setState("yolo", min, max.plusDays(10));

        // Max timestamp is now set to the newer one
        assertThat(stateService.findByEventDefinitionId("yolo"))
                .isPresent()
                .get()
                .satisfies(dto -> {
                    assertThat(dto.minProcessedTimestamp()).isEqualTo(min);
                    assertThat(dto.maxProcessedTimestamp()).isEqualTo(max.plusDays(10));
                });

        // Overwrite state with an OLDER min timestamp
        stateService.setState("yolo", min.minusDays(100), max.plusDays(10));

        // Min timestamp is now set to the older one
        assertThat(stateService.findByEventDefinitionId("yolo"))
                .isPresent()
                .get()
                .satisfies(dto -> {
                    assertThat(dto.minProcessedTimestamp()).isEqualTo(min.minusDays(100));
                    assertThat(dto.maxProcessedTimestamp()).isEqualTo(max.plusDays(10));
                });
    }

}