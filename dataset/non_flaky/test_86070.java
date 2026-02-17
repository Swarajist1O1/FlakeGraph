class DummyClass_86070 {
    @Test
    public void createEventsWithFilter() throws Exception {
        when(eventProcessorDependencyCheck.hasMessagesIndexedUpTo(any(DateTime.class))).thenReturn(true);

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

        assertThatCode(() -> eventProcessor.createEvents(eventFactory, parameters, (events) -> {})).doesNotThrowAnyException();

        verify(moreSearch, times(1)).scrollQuery(
                eq(config.query()),
                eq(config.streams()),
                eq(config.queryParameters()),
                eq(parameters.timerange()),
                eq(parameters.batchSize()),
                any(MoreSearch.ScrollCallback.class)
        );
        verify(searchFactory, never()).create(eq(config), eq(parameters), any(String.class), eq(eventDefinitionDto));
    }

}