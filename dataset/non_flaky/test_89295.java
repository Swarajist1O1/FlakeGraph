class DummyClass_89295 {
  @Test
  public void shouldDoNothingWhenLastModifiedTimeOfOffsetFileIsLessThanOffsetTTL() throws Exception {
    File offsetFile = createOffsetFile(taskStoreDir);
    localStoreMonitor.monitor();
    assertTrue("Offset file should exist.", offsetFile.exists());
    assertEquals(0, localStoreMonitorMetrics.diskSpaceFreedInBytes.getCount());
  }

}