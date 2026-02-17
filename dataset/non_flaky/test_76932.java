class DummyClass_76932 {
  @Test
  public void checkBlockSendResultTest() {
    SparkConf conf = new SparkConf();
    conf.setAppName("testApp")
        .setMaster("local[2]")
        .set(RssClientConfig.RSS_TEST_FLAG, "true")
        .set(RssClientConfig.RSS_WRITER_SEND_CHECK_TIMEOUT, "10000")
        .set(RssClientConfig.RSS_WRITER_SEND_CHECK_INTERVAL, "1000")
        .set(RssClientConfig.RSS_COORDINATOR_QUORUM, "127.0.0.1:12345,127.0.0.1:12346");
    // init SparkContext
    SparkContext sc = SparkContext.getOrCreate(conf);
    Map<String, Set<Long>> failBlocks = Maps.newConcurrentMap();
    Map<String, Set<Long>> successBlocks = Maps.newConcurrentMap();
    Serializer kryoSerializer = new KryoSerializer(conf);
    RssShuffleManager manager = TestUtils.createShuffleManager(
        conf,
        false,
        null,
        successBlocks,
        failBlocks);

    ShuffleWriteClient mockShuffleWriteClient = mock(ShuffleWriteClient.class);
    Partitioner mockPartitioner = mock(Partitioner.class);
    RssShuffleHandle mockHandle = mock(RssShuffleHandle.class);
    ShuffleDependency mockDependency = mock(ShuffleDependency.class);
    when(mockHandle.getDependency()).thenReturn(mockDependency);
    when(mockPartitioner.numPartitions()).thenReturn(2);
    TaskMemoryManager mockTaskMemoryManager = mock(TaskMemoryManager.class);
    when(mockHandle.getPartitionToServers()).thenReturn(Maps.newHashMap());
    when(mockDependency.partitioner()).thenReturn(mockPartitioner);

    BufferManagerOptions bufferOptions = new BufferManagerOptions(conf);
    WriteBufferManager bufferManager = new WriteBufferManager(
        0, 0, bufferOptions, kryoSerializer,
        Maps.newHashMap(), mockTaskMemoryManager, new ShuffleWriteMetrics());
    WriteBufferManager bufferManagerSpy = spy(bufferManager);

    RssShuffleWriter rssShuffleWriter = new RssShuffleWriter("appId", 0, "taskId", 1L,
        bufferManagerSpy, (new TaskMetrics()).shuffleWriteMetrics(),
        manager, conf, mockShuffleWriteClient, mockHandle);
    doReturn(1000000L).when(bufferManagerSpy).acquireMemory(anyLong());

    // case 1: all blocks are sent successfully
    successBlocks.put("taskId", Sets.newHashSet(1L, 2L, 3L));
    rssShuffleWriter.checkBlockSendResult(Sets.newHashSet(1L, 2L, 3L));
    successBlocks.clear();

    // case 2: partial blocks aren't sent before spark.rss.writer.send.check.timeout,
    // Runtime exception will be thrown
    successBlocks.put("taskId", Sets.newHashSet(1L, 2L));
    thrown.expect(RuntimeException.class);
    thrown.expectMessage(StringStartsWith.startsWith("Timeout:"));
    rssShuffleWriter.checkBlockSendResult(Sets.newHashSet(1L, 2L, 3L));
    successBlocks.clear();

    // case 3: partial blocks are sent failed, Runtime exception will be thrown
    successBlocks.put("taskId", Sets.newHashSet(1L, 2L));
    failBlocks.put("taskId", Sets.newHashSet(3L));
    thrown.expect(RuntimeException.class);
    thrown.expectMessage(StringStartsWith.startsWith("Send failed:"));
    rssShuffleWriter.checkBlockSendResult(Sets.newHashSet(1L, 2L, 3L));
    successBlocks.clear();
    failBlocks.clear();

    sc.stop();
  }

}