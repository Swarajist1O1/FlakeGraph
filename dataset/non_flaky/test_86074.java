class DummyClass_86074 {
    @Test
    public void testExtractValuesWithGroupBy() throws Exception {
        final AbsoluteRange timerange = AbsoluteRange.create(DateTime.now(DateTimeZone.UTC).minusSeconds(3600), DateTime.now(DateTimeZone.UTC));
        final AggregationSeries seriesCount = AggregationSeries.create("abc123", AggregationFunction.COUNT, "source");
        final AggregationSeries seriesCard = AggregationSeries.create("abc123", AggregationFunction.CARD, "source");
        final AggregationEventProcessorConfig config = AggregationEventProcessorConfig.builder()
                .query("")
                .streams(Collections.emptySet())
                .groupBy(Collections.emptyList())
                .series(ImmutableList.of(seriesCount, seriesCard))
                .conditions(null)
                .searchWithinMs(30000)
                .executeEveryMs(30000)
                .build();
        final AggregationEventProcessorParameters parameters = AggregationEventProcessorParameters.builder()
                .streams(Collections.emptySet())
                .timerange(timerange)
                .batchSize(500)
                .build();

        final PivotAggregationSearch pivotAggregationSearch = new PivotAggregationSearch(
                config,
                parameters,
                "test",
                eventDefinition,
                searchJobService,
                queryEngine,
                EventsConfigurationTestProvider.create(),
                moreSearch,
                permittedStreams);

        final String toString = timerange.getTo().toString();
        final PivotResult pivotResult = PivotResult.builder()
                .id("test")
                .effectiveTimerange(timerange)
                .total(1)
                .addRow(PivotResult.Row.builder()
                        .key(ImmutableList.of(toString, "a", "b"))
                        .addValue(PivotResult.Value.create(ImmutableList.of("metric/count/source/abc123"), 42, true, "row-leaf"))
                        .addValue(PivotResult.Value.create(ImmutableList.of("metric/card/source/abc123"), 1, true, "row-leaf"))
                        .source("leaf")
                        .build())
                .addRow(PivotResult.Row.builder()
                        .key(ImmutableList.of(toString, "a"))
                        .addValue(PivotResult.Value.create(ImmutableList.of("metric/count/source/abc123"), 84, true, "row-inner"))
                        .addValue(PivotResult.Value.create(ImmutableList.of("metric/card/source/abc123"), 1, true, "row-inner"))
                        .source("non-leaf")
                        .build())
                .addRow(PivotResult.Row.builder()
                        .key(ImmutableList.of(toString, "a", "c"))
                        .addValue(PivotResult.Value.create(ImmutableList.of("metric/count/source/abc123"), 42, true, "row-leaf"))
                        .addValue(PivotResult.Value.create(ImmutableList.of("metric/card/source/abc123"), 1, true, "row-leaf"))
                        .source("leaf")
                        .build())
                .build();

        final ImmutableList<AggregationKeyResult> results = pivotAggregationSearch.extractValues(pivotResult);

        assertThat(results.size()).isEqualTo(2);

        assertThat(results.get(0)).isEqualTo(AggregationKeyResult.builder()
                .timestamp(timerange.getTo())
                .key(ImmutableList.of("a", "b"))
                .seriesValues(ImmutableList.of(
                        AggregationSeriesValue.builder()
                                .key(ImmutableList.of("a", "b"))
                                .value(42.0)
                                .series(seriesCount)
                                .build(),
                        AggregationSeriesValue.builder()
                                .key(ImmutableList.of("a", "b"))
                                .value(1.0)
                                .series(seriesCard)
                                .build()
                ))
                .build());

        assertThat(results.get(1)).isEqualTo(AggregationKeyResult.builder()
                .timestamp(timerange.getTo())
                .key(ImmutableList.of("a", "c"))
                .seriesValues(ImmutableList.of(
                        AggregationSeriesValue.builder()
                                .key(ImmutableList.of("a", "c"))
                                .value(42.0)
                                .series(seriesCount)
                                .build(),
                        AggregationSeriesValue.builder()
                                .key(ImmutableList.of("a", "c"))
                                .value(1.0)
                                .series(seriesCard)
                                .build()
                ))
                .build());
    }

}