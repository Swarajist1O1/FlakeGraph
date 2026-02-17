class DummyClass_89293 {
  @Test
  public void shouldDeleteLocalStoreWhenLastModifiedTimeOfOffsetFileIsGreaterThanOffsetTTL() throws Exception {
    File offsetFile = createOffsetFile(taskStoreDir);
    offsetFile.setLastModified(0);
    localStoreMonitor.monitor();
    assertTrue("Offset file should not exist.", !offsetFile.exists());
    assertEquals(0, localStoreMonitorMetrics.diskSpaceFreedInBytes.getCount());
  }

}