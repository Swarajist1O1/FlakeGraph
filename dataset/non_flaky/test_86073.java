class DummyClass_86073 {
    @Test
    public void testEventsFromAggregationResultWithEmptyResultAndNoConfiguredStreamsUsesAllStreamsAsSourceStreams() {
        final DateTime now = DateTime.now(DateTimeZone.UTC);
        final AbsoluteRange timerange = AbsoluteRange.create(now.minusHours(1), now.plusHours(1));

        // We expect to get the end of the aggregation timerange as event time
        final TestEvent event1 = new TestEvent(timerange.to());
        final TestEvent event2 = new TestEvent(timerange.to());
        when(eventFactory.createEvent(any(EventDefinition.class), eq(now), anyString()))
                .thenReturn(event1)  // first invocation return value
                .thenReturn(event2); // second invocation return value

        when(streamService.loadAll()).thenReturn(ImmutableList.of(
                new StreamMock(Collections.singletonMap("_id", "stream-1"), Collections.emptyList()),
                new StreamMock(Collections.singletonMap("_id", "stream-2"), Collections.emptyList()),
                new StreamMock(Collections.singletonMap("_id", "stream-3"), Collections.emptyList()),
                new StreamMock(Collections.singletonMap("_id", StreamImpl.DEFAULT_STREAM_ID), Collections.emptyList()),
                new StreamMock(Collections.singletonMap("_id", StreamImpl.DEFAULT_EVENTS_STREAM_ID), Collections.emptyList()),
                new StreamMock(Collections.singletonMap("_id", StreamImpl.DEFAULT_SYSTEM_EVENTS_STREAM_ID), Collections.emptyList())
        ));

        final EventDefinitionDto eventDefinitionDto = EventDefinitionDto.builder()
                .id("dto-id-1")
                .title("Test Aggregation")
                .description("A test aggregation event processors")
                .priority(1)
                .alert(false)
                .notificationSettings(EventNotificationSettings.withGracePeriod(60000))
                .config(AggregationEventProcessorConfig.builder()
                        .query("")
                        .streams(ImmutableSet.of()) // No configured streams!
                        .groupBy(ImmutableList.of("group_field_one", "group_field_two"))
                        .series(ImmutableList.of())
                        .conditions(null)
                        .searchWithinMs(30000)
                        .executeEveryMs(30000)
                        .build())
                .keySpec(ImmutableList.of())
                .build();
        final AggregationEventProcessorParameters parameters = AggregationEventProcessorParameters.builder()
                .timerange(timerange)
                .build();

        final AggregationEventProcessor eventProcessor = new AggregationEventProcessor(eventDefinitionDto, searchFactory, eventProcessorDependencyCheck, stateService, moreSearch, streamService, messages);

        final AggregationResult result = AggregationResult.builder()
                .effectiveTimerange(timerange)
                .totalAggregatedMessages(0)
                .sourceStreams(ImmutableSet.of()) // No streams in result
                .keyResults(ImmutableList.of(
                        AggregationKeyResult.builder()
                                .key(ImmutableList.of("one", "two"))
                                .timestamp(now)
                                .seriesValues(ImmutableList.of(
                                        AggregationSeriesValue.builder()
                                                .key(ImmutableList.of("a"))
                                                .value(0.0d)
                                                .series(AggregationSeries.builder()
                                                        .id("abc123")
                                                        .function(AggregationFunction.COUNT)
                                                        .build())
                                                .build()
                                ))
                                .build()
                ))
                .build();

        final ImmutableList<EventWithContext> eventsWithContext = eventProcessor.eventsFromAggregationResult(eventFactory, parameters, result);

        assertThat(eventsWithContext).hasSize(1);

        assertThat(eventsWithContext.get(0)).satisfies(eventWithContext -> {
            final Event event = eventWithContext.event();

            assertThat(event.getId()).isEqualTo(event1.getId());
            assertThat(event.getMessage()).isEqualTo(event1.getMessage());
            assertThat(event.getEventTimestamp()).isEqualTo(timerange.to());
            assertThat(event.getTimerangeStart()).isEqualTo(timerange.from());
            assertThat(event.getTimerangeEnd()).isEqualTo(timerange.to());
            // Must contain all existing streams but the default event streams!
            assertThat(event.getSourceStreams()).containsOnly(
                    "stream-1",
                    "stream-2",
                    "stream-3",
                    StreamImpl.DEFAULT_STREAM_ID
            );

            final Message message = eventWithContext.messageContext().orElse(null);

            assertThat(message).isNotNull();
            assertThat(message.getField("group_field_one")).isEqualTo("one");
            assertThat(message.getField("group_field_two")).isEqualTo("two");
            assertThat(message.getField("aggregation_key")).isEqualTo("one|two");
            assertThat(message.getField("aggregation_value_count")).isEqualTo(0.0d);
        });
    }

}