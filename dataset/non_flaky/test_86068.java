class DummyClass_86068 {
    @Test
    public void testEventsFromAggregationResult() {
        final DateTime now = DateTime.now(DateTimeZone.UTC);
        final AbsoluteRange timerange = AbsoluteRange.create(now.minusHours(1), now.plusHours(1));

        // We expect to get the end of the aggregation timerange as event time
        final TestEvent event1 = new TestEvent(timerange.to());
        final TestEvent event2 = new TestEvent(timerange.to());
        when(eventFactory.createEvent(any(EventDefinition.class), eq(now), anyString()))
                .thenReturn(event1)  // first invocation return value
                .thenReturn(event2); // second invocation return value

        final EventDefinitionDto eventDefinitionDto = EventDefinitionDto.builder()
                .id("dto-id-1")
                .title("Test Aggregation")
                .description("A test aggregation event processors")
                .priority(1)
                .alert(false)
                .notificationSettings(EventNotificationSettings.withGracePeriod(60000))
                .config(AggregationEventProcessorConfig.builder()
                        .query("")
                        .streams(ImmutableSet.of("stream-2"))
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
                .totalAggregatedMessages(1)
                .sourceStreams(ImmutableSet.of("stream-1", "stream-2"))
                .keyResults(ImmutableList.of(
                        AggregationKeyResult.builder()
                                .key(ImmutableList.of("one", "two"))
                                .timestamp(now)
                                .seriesValues(ImmutableList.of(
                                        AggregationSeriesValue.builder()
                                                .key(ImmutableList.of("a"))
                                                .value(42.0d)
                                                .series(AggregationSeries.builder()
                                                        .id("abc123")
                                                        .function(AggregationFunction.COUNT)
                                                        .field("source")
                                                        .build())
                                                .build(),
                                        AggregationSeriesValue.builder()
                                                .key(ImmutableList.of("a"))
                                                .value(23.0d)
                                                .series(AggregationSeries.builder()
                                                        .id("abc123-no-field")
                                                        .function(AggregationFunction.COUNT)
                                                        .build())
                                                .build(),
                                        AggregationSeriesValue.builder()
                                                .key(ImmutableList.of("a"))
                                                .value(1.0d)
                                                .series(AggregationSeries.builder()
                                                        .id("xyz789")
                                                        .function(AggregationFunction.CARD)
                                                        .field("source")
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
            // Should only contain the streams that have been configured in event definition
            assertThat(event.getSourceStreams()).containsOnly("stream-2");

            final Message message = eventWithContext.messageContext().orElse(null);

            assertThat(message).isNotNull();
            assertThat(message.getField("group_field_one")).isEqualTo("one");
            assertThat(message.getField("group_field_two")).isEqualTo("two");
            assertThat(message.getField("aggregation_key")).isEqualTo("one|two");
            assertThat(message.getField("aggregation_value_count_source")).isEqualTo(42.0d);
            // Make sure that the count with a "null" field doesn't include the field in the name
            assertThat(message.getField("aggregation_value_count")).isEqualTo(23.0d);
            assertThat(message.getField("aggregation_value_card_source")).isEqualTo(1.0d);
        });
    }

}