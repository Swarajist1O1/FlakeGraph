class DummyClass_89292 {
  @Test
  public void shouldDeleteLocalTaskStoreWhenItHasNoOffsetFile() throws Exception {
    localStoreMonitor.monitor();
    assertTrue("Task store directory should not exist.", !taskStoreDir.exists());
    assertEquals(taskStoreSize, localStoreMonitorMetrics.diskSpaceFreedInBytes.getCount());
    assertEquals(1, localStoreMonitorMetrics.noOfDeletedTaskPartitionStores.getCount());
  }

}