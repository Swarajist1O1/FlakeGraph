class DummyClass_89296 {
  @Test
  public void shouldDoNothingWhenTheJobIsRunning() throws Exception {
    Mockito.when(jobsClientMock.getJobStatus(Mockito.any())).thenReturn(JobStatus.STARTED);
    File offsetFile = createOffsetFile(taskStoreDir);
    localStoreMonitor.monitor();
    assertTrue("Offset file should exist.", offsetFile.exists());
    assertEquals(0, localStoreMonitorMetrics.diskSpaceFreedInBytes.getCount());
  }

}