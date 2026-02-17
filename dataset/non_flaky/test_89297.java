class DummyClass_89297 {
  @Test
  public void shouldDeleteTaskStoreWhenTaskPreferredStoreIsNotLocalHost() throws Exception {
    Task task = new Task("notLocalHost", "test-task", "0", new ArrayList<>(), ImmutableList.of("test-store"));
    Mockito.when(jobsClientMock.getTasks(Mockito.any())).thenReturn(ImmutableList.of(task));
    localStoreMonitor.monitor();
    assertTrue("Task store directory should not exist.", !taskStoreDir.exists());
    assertEquals(taskStoreSize, localStoreMonitorMetrics.diskSpaceFreedInBytes.getCount());
    assertEquals(1, localStoreMonitorMetrics.noOfDeletedTaskPartitionStores.getCount());
  }

}