class DummyClass_60862 {
  @Test
  public void testTopNWithDistinctCountAgg() throws Exception
  {
    TopNQueryEngine engine = new TopNQueryEngine(pool);

    IncrementalIndex index = new OnheapIncrementalIndex.Builder()
        .setIndexSchema(
            new IncrementalIndexSchema.Builder()
                .withQueryGranularity(Granularities.SECOND)
                .withMetrics(new CountAggregatorFactory("cnt"))
                .build()
        )
        .setMaxRowCount(1000)
        .build();

    String visitor_id = "visitor_id";
    String client_type = "client_type";
    DateTime time = DateTimes.of("2016-03-04T00:00:00.000Z");
    long timestamp = time.getMillis();
    index.add(
        new MapBasedInputRow(
            timestamp,
            Lists.newArrayList(visitor_id, client_type),
            ImmutableMap.of(visitor_id, "0", client_type, "iphone")
        )
    );
    index.add(
        new MapBasedInputRow(
            timestamp,
            Lists.newArrayList(visitor_id, client_type),
            ImmutableMap.of(visitor_id, "1", client_type, "iphone")
        )
    );
    index.add(
        new MapBasedInputRow(
            timestamp,
            Lists.newArrayList(visitor_id, client_type),
            ImmutableMap.of(visitor_id, "2", client_type, "android")
        )
    );

    TopNQuery query = new TopNQueryBuilder().dataSource(QueryRunnerTestHelper.DATA_SOURCE)
                          .granularity(QueryRunnerTestHelper.ALL_GRAN)
                          .intervals(QueryRunnerTestHelper.FULL_ON_INTERVAL_SPEC)
                          .dimension(client_type)
                          .metric("UV")
                          .threshold(10)
                          .aggregators(
                              QueryRunnerTestHelper.ROWS_COUNT,
                              new DistinctCountAggregatorFactory("UV", visitor_id, null)
                          )
                          .build();

    final Iterable<Result<TopNResultValue>> results =
        engine.query(query, new IncrementalIndexStorageAdapter(index), null).toList();

    List<Result<TopNResultValue>> expectedResults = Collections.singletonList(
        new Result<>(
            time,
            new TopNResultValue(
                Arrays.<Map<String, Object>>asList(
                    ImmutableMap.of(
                        client_type, "iphone",
                        "UV", 2L,
                        "rows", 2L
                    ),
                    ImmutableMap.of(
                        client_type, "android",
                        "UV", 1L,
                        "rows", 1L
                    )
                )
            )
        )
    );
    TestHelper.assertExpectedResults(expectedResults, results);
  }

}