class DummyClass_60882 {
  @Test
  public void testCheckSegmentsAndSubmitTasks() throws IOException
  {
    Set<DataSegment> baseSegments = Sets.newHashSet(
        new DataSegment(
            "base",
            Intervals.of("2015-01-02T00Z/2015-01-03T00Z"),
            "2015-01-03",
            ImmutableMap.of(),
            ImmutableList.of("dim1", "dim2"),
            ImmutableList.of("m1"),
            new HashBasedNumberedShardSpec(0, 1, 0, 1, null, null, null),
            9,
            1024
        )
    );
    indexerMetadataStorageCoordinator.announceHistoricalSegments(baseSegments);
    EasyMock.expect(taskMaster.getTaskQueue()).andReturn(Optional.of(taskQueue)).anyTimes();
    EasyMock.expect(taskMaster.getTaskRunner()).andReturn(Optional.absent()).anyTimes();
    EasyMock.expect(taskStorage.getActiveTasks()).andReturn(ImmutableList.of()).anyTimes();
    EasyMock.expect(taskStorage.getStatus("test_task1"))
            .andReturn(Optional.of(TaskStatus.failure("test_task1", "Dummy task status failure err message")))
            .anyTimes();
    EasyMock.expect(taskStorage.getStatus("test_task2"))
            .andReturn(Optional.of(TaskStatus.running("test_task2")))
            .anyTimes();
    EasyMock.replay(taskStorage);

    Pair<Map<Interval, HadoopIndexTask>, Map<Interval, String>> runningTasksPair = supervisor.getRunningTasks();
    Map<Interval, HadoopIndexTask> runningTasks = runningTasksPair.lhs;
    Map<Interval, String> runningVersion = runningTasksPair.rhs;

    DataSchema dataSchema = new DataSchema(
        "test_datasource",
        null,
        null,
        null,
        TransformSpec.NONE,
        objectMapper
    );
    HadoopIOConfig hadoopIOConfig = new HadoopIOConfig(new HashMap<>(), null, null);
    HadoopIngestionSpec spec = new HadoopIngestionSpec(dataSchema, hadoopIOConfig, null);
    HadoopIndexTask task1 = new HadoopIndexTask(
        "test_task1",
        spec,
        null,
        null,
        null,
        objectMapper,
        null,
        null,
        null
    );
    runningTasks.put(Intervals.of("2015-01-01T00Z/2015-01-02T00Z"), task1);
    runningVersion.put(Intervals.of("2015-01-01T00Z/2015-01-02T00Z"), "test_version1");

    HadoopIndexTask task2 = new HadoopIndexTask(
        "test_task2",
        spec,
        null,
        null,
        null,
        objectMapper,
        null,
        null,
        null
    );
    runningTasks.put(Intervals.of("2015-01-02T00Z/2015-01-03T00Z"), task2);
    runningVersion.put(Intervals.of("2015-01-02T00Z/2015-01-03T00Z"), "test_version2");

    supervisor.checkSegmentsAndSubmitTasks();

    Map<Interval, HadoopIndexTask> expectedRunningTasks = new HashMap<>();
    Map<Interval, String> expectedRunningVersion = new HashMap<>();
    expectedRunningTasks.put(Intervals.of("2015-01-02T00Z/2015-01-03T00Z"), task2);
    expectedRunningVersion.put(Intervals.of("2015-01-02T00Z/2015-01-03T00Z"), "test_version2");

    Assert.assertEquals(expectedRunningTasks, runningTasks);
    Assert.assertEquals(expectedRunningVersion, runningVersion);

  }

}