class DummyClass_86071 {
    @Test
    public void createEventsWithoutRequiredMessagesBeingIndexed() throws Exception {
        final DateTime now = DateTime.now(DateTimeZone.UTC);
        final AbsoluteRange timerange = AbsoluteRange.create(now.minusHours(1), now.plusHours(1));

        final AggregationEventProcessorConfig config = AggregationEventProcessorConfig.builder()
                .query("")
                .streams(ImmutableSet.of())
                .groupBy(ImmutableList.of())
                .series(ImmutableList.of())
                .conditions(null)
                .searchWithinMs(30000)
                .executeEveryMs(30000)
                .build();
        final EventDefinitionDto eventDefinitionDto = EventDefinitionDto.builder()
                .id("dto-id-1")
                .title("Test Aggregation")
                .description("A test aggregation event processors")
                .priority(1)
                .alert(false)
                .notificationSettings(EventNotificationSettings.withGracePeriod(60000))
                .config(config)
                .keySpec(ImmutableList.of())
                .build();
        final AggregationEventProcessorParameters parameters = AggregationEventProcessorParameters.builder()
                .timerange(timerange)
                .build();

        final AggregationEventProcessor eventProcessor = new AggregationEventProcessor(eventDefinitionDto, searchFactory, eventProcessorDependencyCheck, stateService, moreSearch, streamService, messages);

        // If the dependency check returns true, there should be no exception raised and the state service should be called
        when(eventProcessorDependencyCheck.hasMessagesIndexedUpTo(timerange.to())).thenReturn(true);

        assertThatCode(() -> eventProcessor.createEvents(eventFactory, parameters, (events) -> {})).doesNotThrowAnyException();

        verify(stateService, times(1)).setState("dto-id-1", timerange.from(), timerange.to());
        verify(moreSearch, times(1)).scrollQuery(
                eq(config.query()),
                eq(config.streams()),
                eq(config.queryParameters()),
                eq(parameters.timerange()),
                eq(parameters.batchSize()),
                any(MoreSearch.ScrollCallback.class)
        );

        reset(stateService, moreSearch, searchFactory); // Rest mocks so we can verify it again

        // If the dependency check returns false, a precondition exception should be raised and the state service not be called
        when(eventProcessorDependencyCheck.hasMessagesIndexedUpTo(timerange.to())).thenReturn(false);

        assertThatCode(() -> eventProcessor.createEvents(eventFactory, parameters, (events) -> {}))
                .hasMessageContaining(eventDefinitionDto.title())
                .hasMessageContaining(eventDefinitionDto.id())
                .hasMessageContaining(timerange.from().toString())
                .hasMessageContaining(timerange.to().toString())
                .isInstanceOf(EventProcessorPreconditionException.class);

        verify(stateService, never()).setState(any(String.class), any(DateTime.class), any(DateTime.class));
        verify(searchFactory, never()).create(any(), any(), any(), any());
        verify(moreSearch, never()).scrollQuery(
                eq(config.query()),
                eq(config.streams()),
                eq(config.queryParameters()),
                eq(parameters.timerange()),
                eq(parameters.batchSize()),
                any(MoreSearch.ScrollCallback.class)
        );
    }

}