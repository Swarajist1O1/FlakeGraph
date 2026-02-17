class DummyClass_60864 {
  @Test
  public void testGroupByWithDistinctCountAgg() throws Exception
  {
    IncrementalIndex index = new OnheapIncrementalIndex.Builder()
        .setIndexSchema(
            new IncrementalIndexSchema.Builder()
                .withQueryGranularity(Granularities.SECOND)
                .withMetrics(new CountAggregatorFactory("cnt"))
                .build()
        )
        .setConcurrentEventAdd(true)
        .setMaxRowCount(1000)
        .build();

    String visitor_id = "visitor_id";
    String client_type = "client_type";
    long timestamp = DateTimes.of("2010-01-01").getMillis();
    index.add(
        new MapBasedInputRow(
            timestamp,
            Lists.newArrayList(visitor_id, client_type),
            ImmutableMap.of(visitor_id, "0", client_type, "iphone")
        )
    );
    index.add(
        new MapBasedInputRow(
            timestamp + 1,
            Lists.newArrayList(visitor_id, client_type),
            ImmutableMap.of(visitor_id, "1", client_type, "iphone")
        )
    );
    index.add(
        new MapBasedInputRow(
            timestamp + 2,
            Lists.newArrayList(visitor_id, client_type),
            ImmutableMap.of(visitor_id, "2", client_type, "android")
        )
    );

    GroupByQuery query = new GroupByQuery.Builder()
        .setDataSource(QueryRunnerTestHelper.DATA_SOURCE)
        .setGranularity(QueryRunnerTestHelper.ALL_GRAN)
        .setDimensions(new DefaultDimensionSpec(
            client_type,
            client_type
        ))
        .setInterval(QueryRunnerTestHelper.FULL_ON_INTERVAL_SPEC)
        .setLimitSpec(
            new DefaultLimitSpec(
                Collections.singletonList(new OrderByColumnSpec(client_type, OrderByColumnSpec.Direction.DESCENDING)),
                10
            )
        )
        .setAggregatorSpecs(QueryRunnerTestHelper.ROWS_COUNT, new DistinctCountAggregatorFactory("UV", visitor_id, null))
        .build();
    final Segment incrementalIndexSegment = new IncrementalIndexSegment(index, null);

    Iterable<ResultRow> results = GroupByQueryRunnerTestHelper.runQuery(
        factory,
        factory.createRunner(incrementalIndexSegment),
        query
    );

    List<ResultRow> expectedResults = Arrays.asList(
        GroupByQueryRunnerTestHelper.createExpectedRow(
            query,
            "1970-01-01T00:00:00.000Z",
            client_type, "iphone",
            "UV", 2L,
            "rows", 2L
        ),
        GroupByQueryRunnerTestHelper.createExpectedRow(
            query,
            "1970-01-01T00:00:00.000Z",
            client_type, "android",
            "UV", 1L,
            "rows", 1L
        )
    );
    TestHelper.assertExpectedObjects(expectedResults, results, "distinct-count");
  }

}