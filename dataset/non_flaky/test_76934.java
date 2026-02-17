class DummyClass_76934 {
  @Test
  public void postBlockEventTest() throws Exception {
    WriteBufferManager mockBufferManager = mock(WriteBufferManager.class);
    ShuffleDependency mockDependency = mock(ShuffleDependency.class);
    ShuffleWriteMetrics mockMetrics = mock(ShuffleWriteMetrics.class);
    Partitioner mockPartitioner = mock(Partitioner.class);
    when(mockDependency.partitioner()).thenReturn(mockPartitioner);
    SparkConf sparkConf = new SparkConf();
    when(mockPartitioner.numPartitions()).thenReturn(2);
    List<AddBlockEvent> events = Lists.newArrayList();

    EventLoop<AddBlockEvent> eventLoop = new EventLoop<AddBlockEvent>("test") {
      @Override
      public void onReceive(AddBlockEvent event) {
        events.add(event);
      }

}