class DummyClass_60957 {
  @Test
  public void testResultArraySignature()
  {
    final TimeseriesQuery query =
        Druids.newTimeseriesQueryBuilder()
              .dataSource("dummy")
              .intervals("2000/3000")
              .granularity(Granularities.HOUR)
              .aggregators(
                  new CountAggregatorFactory("count"),
                  new TDigestSketchAggregatorFactory("tdigest", "col", null)
              )
              .postAggregators(
                  new FieldAccessPostAggregator("tdigest-access", "tdigest"),
                  new FinalizingFieldAccessPostAggregator("tdigest-finalize", "tdigest")
              )
              .build();

    Assert.assertEquals(
        RowSignature.builder()
                    .addTimeColumn()
                    .add("count", ColumnType.LONG)
                    .add("tdigest", TDigestSketchAggregatorFactory.TYPE)
                    .add("tdigest-access", TDigestSketchAggregatorFactory.TYPE)
                    .add("tdigest-finalize", TDigestSketchAggregatorFactory.TYPE)
                    .build(),
        new TimeseriesQueryQueryToolChest().resultArraySignature(query)
    );
  }

}