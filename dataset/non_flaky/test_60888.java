class DummyClass_60888 {
  @Test
  public void testMaterializedViewSupervisorSpecCreated()
  {
    Exception ex = null;

    try {
      MaterializedViewSupervisorSpec spec = new MaterializedViewSupervisorSpec(
              "wikiticker",
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
      Supervisor supervisor = spec.createSupervisor();
      Assert.assertTrue(supervisor instanceof MaterializedViewSupervisor);

      SupervisorTaskAutoScaler autoscaler = spec.createAutoscaler(supervisor);
      Assert.assertNull(autoscaler);

      try {
        supervisor.computeLagStats();
      }
      catch (Exception e) {
        Assert.assertTrue(e instanceof UnsupportedOperationException);
      }

      try {
        int count = supervisor.getActiveTaskGroupsCount();
      }
      catch (Exception e) {
        Assert.assertTrue(e instanceof UnsupportedOperationException);
      }

      Callable<Integer> noop = new Callable<Integer>() {
        @Override
        public Integer call()
        {
          return -1;
        }

}