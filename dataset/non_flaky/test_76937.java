class DummyClass_76937 {
  @Test
  public void writeTest() throws Exception {
    SparkConf conf = new SparkConf();
    conf.setAppName("testApp").setMaster("local[2]")
        .set(RssClientConfig.RSS_TEST_FLAG, "true")
        .set(RssClientConfig.RSS_WRITER_BUFFER_SIZE, "32")
        .set(RssClientConfig.RSS_WRITER_SERIALIZER_BUFFER_SIZE, "32")
        .set(RssClientConfig.RSS_WRITER_BUFFER_SEGMENT_SIZE, "64")
        .set(RssClientConfig.RSS_WRITER_BUFFER_SPILL_SIZE, "128")
        .set(RssClientConfig.RSS_WRITER_SEND_CHECK_TIMEOUT, "10000")
        .set(RssClientConfig.RSS_WRITER_SEND_CHECK_INTERVAL, "1000")
        .set(RssClientConfig.RSS_COORDINATOR_QUORUM, "127.0.0.1:12345,127.0.0.1:12346");
    // init SparkContext
    SparkContext sc = SparkContext.getOrCreate(conf);
    RssShuffleManager manager = new RssShuffleManager(conf, false);
    List<ShuffleBlockInfo> shuffleBlockInfos = Lists.newArrayList();

    manager.setEventLoop(new EventLoop<AddBlockEvent>("test") {
      @Override
      public void onReceive(AddBlockEvent event) {
        assertEquals("taskId", event.getTaskId());
        shuffleBlockInfos.addAll(event.getShuffleDataInfoList());
        Set<Long> blockIds = event.getShuffleDataInfoList().parallelStream()
            .map(sdi -> sdi.getBlockId()).collect(Collectors.toSet());
        manager.addSuccessBlockIds(event.getTaskId(), blockIds);
      }

}