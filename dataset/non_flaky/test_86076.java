class DummyClass_86076 {
    @Test
    public void testExtractValuesWithNullValues() throws Exception {
        final AbsoluteRange timerange = AbsoluteRange.create(DateTime.now(DateTimeZone.UTC).minusSeconds(3600), DateTime.now(DateTimeZone.UTC));
        final AggregationSeries seriesCount = AggregationSeries.create("abc123", AggregationFunction.COUNT, "source");
        final AggregationSeries seriesAvg = AggregationSeries.create("abc123", AggregationFunction.AVG, "some_field");
        final AggregationEventProcessorConfig config = AggregationEventProcessorConfig.builder()
                .query("")
                .streams(Collections.emptySet())
                .groupBy(Collections.emptyList())
                .series(ImmutableList.of(seriesCount, seriesAvg))
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
                        // A "null" value can happen with some Elasticsearch aggregations (e.g. avg on a non-existent field)
                        .addValue(PivotResult.Value.create(ImmutableList.of("metric/avg/some_field/abc123"), null, true, "row-leaf"))
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
                                .value(Double.NaN) // For "null" we expect NaN
                                .series(seriesAvg)
                                .build()
                ))
                .build());
    }

}