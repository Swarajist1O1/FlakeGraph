class DummyClass_86106 {
    @Test
    public void setState() {
        final DateTime now = DateTime.now(DateTimeZone.UTC);

        // Before we set the state, there should be no record
        assertThat(stateService.findByEventDefinitionId("yolo")).isNotPresent();

        assertThat(stateService.setState("yolo", now.minusHours(1), now))
                .isPresent()
                .get()
                .satisfies(dto1 -> {
                    assertThat(dto1.minProcessedTimestamp()).isEqualTo(now.minusHours(1));
                    assertThat(dto1.maxProcessedTimestamp()).isEqualTo(now);
                    assertThat(dto1.eventDefinitionId()).isEqualTo("yolo");

                    assertThat(stateService.setState("yolo", now, now.plusHours(1)))
                            .isPresent()
                            .get()
                            .satisfies(dto2 -> {
                                // The second setState call should update the existing one
                                assertThat(dto2.id()).isEqualTo(dto1.id());
                                assertThat(dto2.eventDefinitionId()).isEqualTo("yolo");
                                assertThat(dto2.minProcessedTimestamp()).isEqualTo(dto1.minProcessedTimestamp());
                                assertThat(dto2.maxProcessedTimestamp()).isEqualTo(dto1.maxProcessedTimestamp().plusHours(1));
                            });
                });
    }

}