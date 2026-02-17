class DummyClass_76933 {
  @Test
  public void writeTest() throws Exception {
    SparkConf conf = new SparkConf();
    conf.setAppName("testApp").setMaster("local[2]")
        .set(RssClientConfig.RSS_WRITER_SERIALIZER_BUFFER_SIZE, "32")
        .set(RssClientConfig.RSS_WRITER_BUFFER_SIZE, "32")
        .set(RssClientConfig.RSS_TEST_FLAG, "true")
        .set(RssClientConfig.RSS_WRITER_BUFFER_SEGMENT_SIZE, "64")
        .set(RssClientConfig.RSS_WRITER_SEND_CHECK_TIMEOUT, "10000")
        .set(RssClientConfig.RSS_WRITER_SEND_CHECK_INTERVAL, "1000")
        .set(RssClientConfig.RSS_WRITER_BUFFER_SPILL_SIZE, "128")
        .set(RssClientConfig.RSS_COORDINATOR_QUORUM, "127.0.0.1:12345,127.0.0.1:12346");
    // init SparkContext
    List<ShuffleBlockInfo> shuffleBlockInfos = Lists.newArrayList();
    SparkContext sc = SparkContext.getOrCreate(conf);
    Map<String, Set<Long>> successBlockIds = Maps.newConcurrentMap();
    EventLoop<AddBlockEvent> testLoop = new EventLoop<AddBlockEvent>("test") {
      @Override
      public void onReceive(AddBlockEvent event) {
        assertEquals("taskId", event.getTaskId());
        shuffleBlockInfos.addAll(event.getShuffleDataInfoList());
        Set<Long> blockIds = event.getShuffleDataInfoList().parallelStream()
            .map(sdi -> sdi.getBlockId()).collect(Collectors.toSet());
        successBlockIds.putIfAbsent(event.getTaskId(), Sets.newConcurrentHashSet());
        successBlockIds.get(event.getTaskId()).addAll(blockIds);
      }

}