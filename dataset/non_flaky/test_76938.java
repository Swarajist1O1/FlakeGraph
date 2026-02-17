class DummyClass_76938 {
  @Test
  public void postBlockEventTest() throws Exception {
    WriteBufferManager mockBufferManager = mock(WriteBufferManager.class);
    ShuffleWriteMetrics mockMetrics = mock(ShuffleWriteMetrics.class);
    ShuffleDependency mockDependency = mock(ShuffleDependency.class);
    Partitioner mockPartitioner = mock(Partitioner.class);
    RssShuffleManager mockShuffleManager = mock(RssShuffleManager.class);
    when(mockDependency.partitioner()).thenReturn(mockPartitioner);
    when(mockPartitioner.numPartitions()).thenReturn(2);
    List<AddBlockEvent> events = Lists.newArrayList();

    EventLoop<AddBlockEvent> eventLoop = new EventLoop<AddBlockEvent>("test") {
      @Override
      public void onReceive(AddBlockEvent event) {
        events.add(event);
      }

}