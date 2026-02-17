class DummyClass_76935 {
  @Test
  public void readTest() throws Exception {

    String basePath = HDFS_URI + "readTest1";
    HdfsShuffleWriteHandler writeHandler =
        new HdfsShuffleWriteHandler("appId", 0, 0, 1, basePath, "test", conf);

    Map<String, String> expectedData = Maps.newHashMap();
    Roaring64NavigableMap blockIdBitmap = Roaring64NavigableMap.bitmapOf();
    Roaring64NavigableMap taskIdBitmap = Roaring64NavigableMap.bitmapOf(0);
     writeTestData(writeHandler, 2, 5, expectedData,
        blockIdBitmap, "key", KRYO_SERIALIZER, 0);

    TaskContext contextMock = mock(TaskContext.class);
    RssShuffleHandle handleMock = mock(RssShuffleHandle.class);
    ShuffleDependency dependencyMock = mock(ShuffleDependency.class);
    when(handleMock.getAppId()).thenReturn("appId");
    when(handleMock.getShuffleId()).thenReturn(1);
    when(handleMock.getDependency()).thenReturn(dependencyMock);
    when(dependencyMock.serializer()).thenReturn(KRYO_SERIALIZER);
    when(contextMock.taskAttemptId()).thenReturn(1L);
    when(contextMock.attemptNumber()).thenReturn(1);
    when(contextMock.taskMetrics()).thenReturn(new TaskMetrics());
    doNothing().when(contextMock).killTaskIfInterrupted();
    when(dependencyMock.mapSideCombine()).thenReturn(false);
    when(dependencyMock.aggregator()).thenReturn(Option.empty());
    when(dependencyMock.keyOrdering()).thenReturn(Option.empty());

    RssShuffleReader rssShuffleReaderSpy = spy(new RssShuffleReader<String, String>(0, 1, contextMock,
        handleMock, basePath, 1000, conf, StorageType.HDFS.name(),
        1000, 2, 10, blockIdBitmap, taskIdBitmap));

    validateResult(rssShuffleReaderSpy.read(), expectedData, 10);
  }

}