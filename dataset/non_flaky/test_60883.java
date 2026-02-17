class DummyClass_60883 {
  @Test
  public void testCreateTask()
  {
    List<DataSegment> baseSegments = Collections.singletonList(
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

    HadoopIndexTask task = spec.createTask(
        Intervals.of("2015-01-02T00Z/2015-01-03T00Z"),
        "2015-01-03",
        baseSegments
    );

    Assert.assertNotNull(task);
  }

}