class DummyClass_60881 {
  @Test
  public void testCheckSegments() throws IOException
  {
    Set<DataSegment> baseSegments = Sets.newHashSet(
        new DataSegment(
            "base",
            Intervals.of("2015-01-01T00Z/2015-01-02T00Z"),
            "2015-01-02",
            ImmutableMap.of(),
            ImmutableList.of("dim1", "dim2"),
            ImmutableList.of("m1"),
            new HashBasedNumberedShardSpec(0, 1, 0, 1, null, null, null),
            9,
            1024
        ),
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
        ),
        new DataSegment(
            "base",
            Intervals.of("2015-01-03T00Z/2015-01-04T00Z"),
            "2015-01-04",
            ImmutableMap.of(),
            ImmutableList.of("dim1", "dim2"),
            ImmutableList.of("m1"),
            new HashBasedNumberedShardSpec(0, 1, 0, 1, null, null, null),
            9,
            1024
        )
    );
    Set<DataSegment> derivativeSegments = Sets.newHashSet(
        new DataSegment(
            derivativeDatasourceName,
            Intervals.of("2015-01-01T00Z/2015-01-02T00Z"),
            "2015-01-02",
            ImmutableMap.of(),
            ImmutableList.of("dim1", "dim2"),
            ImmutableList.of("m1"),
            new HashBasedNumberedShardSpec(0, 1, 0, 1, null, null, null),
            9,
            1024
        ),
        new DataSegment(
            derivativeDatasourceName,
            Intervals.of("2015-01-02T00Z/2015-01-03T00Z"),
            "3015-01-01",
            ImmutableMap.of(),
            ImmutableList.of("dim1", "dim2"),
            ImmutableList.of("m1"),
            new HashBasedNumberedShardSpec(0, 1, 0, 1, null, null, null),
            9,
            1024
        )
    );
    indexerMetadataStorageCoordinator.announceHistoricalSegments(baseSegments);
    indexerMetadataStorageCoordinator.announceHistoricalSegments(derivativeSegments);
    EasyMock.expect(taskMaster.getTaskQueue()).andReturn(Optional.of(taskQueue)).anyTimes();
    EasyMock.expect(taskMaster.getTaskRunner()).andReturn(Optional.absent()).anyTimes();
    EasyMock.expect(taskStorage.getActiveTasks()).andReturn(ImmutableList.of()).anyTimes();
    Pair<SortedMap<Interval, String>, Map<Interval, List<DataSegment>>> toBuildInterval = supervisor.checkSegments();
    Set<Interval> expectedToBuildInterval = Sets.newHashSet(Intervals.of("2015-01-01T00Z/2015-01-02T00Z"));
    Map<Interval, List<DataSegment>> expectedSegments = new HashMap<>();
    expectedSegments.put(
        Intervals.of("2015-01-01T00Z/2015-01-02T00Z"),
        Collections.singletonList(
            new DataSegment(
                "base",
                Intervals.of("2015-01-01T00Z/2015-01-02T00Z"),
                "2015-01-02",
                ImmutableMap.of(),
                ImmutableList.of("dim1", "dim2"),
                ImmutableList.of("m1"),
                new HashBasedNumberedShardSpec(0, 1, 0, 1, null, null, null),
                9,
                1024
            )
        )
    );
    expectedSegments.put(
        Intervals.of("2015-01-02T00Z/2015-01-03T00Z"),
        Collections.singletonList(
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
        )
    );
    Assert.assertEquals(expectedToBuildInterval, toBuildInterval.lhs.keySet());
    Assert.assertEquals(expectedSegments, toBuildInterval.rhs);
  }

}