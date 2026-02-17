class DummyClass_35661 {
  @Test
  public void testSparkWithObjectStore() throws Exception {
    ApplicationManager applicationManager = deploy(NamespaceId.DEFAULT, SparkAppUsingObjectStore.class);

    DataSetManager<ObjectStore<String>> keysManager = getDataset("keys");
    prepareInputData(keysManager);

    SparkManager sparkManager = applicationManager.getSparkManager(CharCountProgram.class.getSimpleName()).start();
    sparkManager.waitForRun(ProgramRunStatus.RUNNING, 10, TimeUnit.SECONDS);
    sparkManager.waitForStopped(60, TimeUnit.SECONDS);

    DataSetManager<KeyValueTable> countManager = getDataset("count");
    checkOutputData(countManager);

    // validate that the table emitted metrics
    // one read + one write in beforeSubmit(), increment (= read + write) in main -> 4
    Tasks.waitFor(4L, new Callable<Long>() {
      @Override
      public Long call() throws Exception {
        Collection<MetricTimeSeries> metrics =
          getMetricsManager().query(new MetricDataQuery(
            0,
            System.currentTimeMillis() / 1000L,
            Integer.MAX_VALUE,
            "system." + Constants.Metrics.Name.Dataset.OP_COUNT,
            AggregationFunction.SUM,
            ImmutableMap.of(Constants.Metrics.Tag.NAMESPACE, DefaultId.NAMESPACE.getNamespace(),
                            Constants.Metrics.Tag.APP, SparkAppUsingObjectStore.class.getSimpleName(),
                            Constants.Metrics.Tag.SPARK, CharCountProgram.class.getSimpleName(),
                            Constants.Metrics.Tag.DATASET, "totals"),
            Collections.<String>emptyList()));
        if (metrics.isEmpty()) {
          return 0L;
        }
        Assert.assertEquals(1, metrics.size());
        MetricTimeSeries ts = metrics.iterator().next();
        Assert.assertEquals(1, ts.getTimeValues().size());
        return ts.getTimeValues().get(0).getValue();
      }

}