class DummyClass_86047 {
    @Test
    public void canProcessTimerange() {
        final DateTime now = DateTime.now(DateTimeZone.UTC);

        final EventProcessorStateDto stateDto1 = EventProcessorStateDto.builder()
                .eventDefinitionId("a")
                .minProcessedTimestamp(now.minusDays(1))
                .maxProcessedTimestamp(now)
                .build();
        final EventProcessorStateDto stateDto2 = EventProcessorStateDto.builder()
                .eventDefinitionId("b")
                .minProcessedTimestamp(now.minusDays(1))
                .maxProcessedTimestamp(now.minusHours(1))
                .build();
        final EventProcessorStateDto stateDto3 = EventProcessorStateDto.builder()
                .eventDefinitionId("c")
                .minProcessedTimestamp(now.minusDays(1))
                .maxProcessedTimestamp(now.minusHours(2))
                .build();

        // No state objects yet
        assertThat(dependencyCheck.canProcessTimerange(now, ImmutableSet.of("a"))).isFalse();

        stateService.setState(stateDto1);
        stateService.setState(stateDto2);
        stateService.setState(stateDto3);

        // No state object has processedTimerageEnd >= now + 1h
        assertThat(dependencyCheck.canProcessTimerange(now.plusHours(1), ImmutableSet.of("a"))).isFalse();

        // Only processor "a" has been processed at "now"
        assertThat(dependencyCheck.canProcessTimerange(now, ImmutableSet.of("a"))).isTrue();
        assertThat(dependencyCheck.canProcessTimerange(now, ImmutableSet.of("a", "b"))).isFalse();
        assertThat(dependencyCheck.canProcessTimerange(now, ImmutableSet.of("a", "c"))).isFalse();
        assertThat(dependencyCheck.canProcessTimerange(now, ImmutableSet.of("a", "b", "c"))).isFalse();
        assertThat(dependencyCheck.canProcessTimerange(now, ImmutableSet.of("b"))).isFalse();
        assertThat(dependencyCheck.canProcessTimerange(now, ImmutableSet.of("c"))).isFalse();

        // Only processors "a" and "b" have been processed at now - 1h
        assertThat(dependencyCheck.canProcessTimerange(now.minusHours(1), ImmutableSet.of("a", "b"))).isTrue();
        assertThat(dependencyCheck.canProcessTimerange(now.minusHours(1), ImmutableSet.of("a", "c"))).isFalse();

        // Processors "a", "b" and "c" have been processed at now - 2h
        assertThat(dependencyCheck.canProcessTimerange(now.minusHours(2), ImmutableSet.of("a", "b", "c"))).isTrue();
        assertThat(dependencyCheck.canProcessTimerange(now.minusHours(2), ImmutableSet.of("a", "b"))).isTrue();
        assertThat(dependencyCheck.canProcessTimerange(now.minusHours(2), ImmutableSet.of("a", "c"))).isTrue();
        assertThat(dependencyCheck.canProcessTimerange(now.minusHours(2), ImmutableSet.of("a"))).isTrue();
        assertThat(dependencyCheck.canProcessTimerange(now.minusHours(2), ImmutableSet.of("b"))).isTrue();
        assertThat(dependencyCheck.canProcessTimerange(now.minusHours(2), ImmutableSet.of("c"))).isTrue();
    }

}