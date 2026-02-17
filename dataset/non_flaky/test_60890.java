class DummyClass_60890 {
  @Test
  public void testEmptyBaseDataSource()
  {
    expectedException.expect(CoreMatchers.instanceOf(IllegalArgumentException.class));
    expectedException.expectMessage(
        "baseDataSource cannot be null or empty. Please provide a baseDataSource."
    );
    //noinspection ResultOfObjectAllocationIgnored (this method call will trigger the expected exception)
    new MaterializedViewSupervisorSpec(
        "",
        new DimensionsSpec(
            Lists.newArrayList(
                new StringDimensionSchema("isUnpatrolled"),
                new StringDimensionSchema("metroCode"),
                new StringDimensionSchema("namespace"),
                new StringDimensionSchema("page"),
                new StringDimensionSchema("regionIsoCode"),
                new StringDimensionSchema("regionName"),
                new StringDimensionSchema("user")
            ),
            null,
            null
        ),
        new AggregatorFactory[]{
            new CountAggregatorFactory("count"),
            new LongSumAggregatorFactory("added", "added")
        },
        HadoopTuningConfig.makeDefaultTuningConfig(),
        null,
        null,
        null,
        null,
        null,
        false,
        objectMapper,
        null,
        null,
        null,
        null,
        null,
        new MaterializedViewTaskConfig(),
        EasyMock.createMock(AuthorizerMapper.class),
        new NoopChatHandlerProvider(),
        new SupervisorStateManagerConfig()
    );
  }

}