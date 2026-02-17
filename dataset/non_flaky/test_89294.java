class DummyClass_89294 {
  @Test
  public void shouldDeleteInActiveLocalStoresOfTheJob() throws Exception {
    File inActiveStoreDir = new File(jobDir, "inActiveStore");
    FileUtils.forceMkdir(inActiveStoreDir);
    File inActiveTaskDir = new File(inActiveStoreDir, "test-task");
    FileUtils.forceMkdir(inActiveTaskDir);
    long inActiveTaskDirSize = inActiveTaskDir.getTotalSpace();
    localStoreMonitor.monitor();
    assertTrue("Inactive task store directory should not exist.", !inActiveTaskDir.exists());
    assertEquals(taskStoreSize + inActiveTaskDirSize, localStoreMonitorMetrics.diskSpaceFreedInBytes.getCount());
    assertEquals(2, localStoreMonitorMetrics.noOfDeletedTaskPartitionStores.getCount());
    FileUtils.deleteDirectory(inActiveStoreDir);
  }

}