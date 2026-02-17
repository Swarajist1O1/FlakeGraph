class DummyClass_86075 {
    @Test
    public void testExtractValuesWithoutGroupBy() throws Exception {
        final AbsoluteRange timerange = AbsoluteRange.create(DateTime.now(DateTimeZone.UTC).minusSeconds(3600), DateTime.now(DateTimeZone.UTC));
        final AggregationSeries seriesCount = AggregationSeries.create("abc123", AggregationFunction.COUNT, "source");
        final AggregationSeries seriesCountNoField = AggregationSeries.create("abc123", AggregationFunction.COUNT, "");
        final AggregationSeries seriesCard = AggregationSeries.create("abc123", AggregationFunction.CARD, "source");
        final AggregationEventProcessorConfig config = AggregationEventProcessorConfig.builder()
                .query("")
                .streams(Collections.emptySet())
                .groupBy(Collections.emptyList())
                .series(ImmutableList.of(seriesCount, seriesCountNoField, seriesCard))
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

        final PivotResult pivotResult = PivotResult.builder()
                .id("test")
                .effectiveTimerange(timerange)
                .total(1)
                .addRow(PivotResult.Row.builder()
                        .key(ImmutableList.of(timerange.getTo().toString()))
                        .addValue(PivotResult.Value.create(ImmutableList.of("metric/count/source/abc123"), 42, true, "row-leaf"))
                        .addValue(PivotResult.Value.create(ImmutableList.of("metric/count/<no-field>/abc123"), 23, true, "row-leaf"))
                        .addValue(PivotResult.Value.create(ImmutableList.of("metric/card/source/abc123"), 1, true, "row-leaf"))
                        .source("leaf")
                        .build())
                .build();

        final ImmutableList<AggregationKeyResult> results = pivotAggregationSearch.extractValues(pivotResult);

        assertThat(results.size()).isEqualTo(1);

        assertThat(results.get(0)).isEqualTo(AggregationKeyResult.builder()
                .key(ImmutableList.of())
                .timestamp(timerange.getTo())
                .seriesValues(ImmutableList.of(
                        AggregationSeriesValue.builder()
                                .key(ImmutableList.of())
                                .value(42.0)
                                .series(seriesCount)
                                .build(),
                        AggregationSeriesValue.builder()
                                .key(ImmutableList.of())
                                .value(23.0)
                                .series(seriesCountNoField)
                                .build(),
                        AggregationSeriesValue.builder()
                                .key(ImmutableList.of())
                                .value(1.0)
                                .series(seriesCard)
                                .build()
                ))
                .build());
    }

}