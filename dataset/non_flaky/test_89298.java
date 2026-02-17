class DummyClass_89298 {
  @Test
  public void shouldContinueLocalStoreCleanUpAfterFailureToCleanUpStoreOfAJob() throws Exception {
    File testFailingJobDir = new File(localStoreDir, "test-jobName-jobId-1");

    File testFailingTaskStoreDir = new File(new File(testFailingJobDir, "test-store"), "test-task");

    FileUtils.forceMkdir(testFailingTaskStoreDir);

    // For job: test-jobName-jobId-1, throw up in getTasks call and
    // expect the cleanup to succeed for other job: test-jobName-jobId.
    Mockito.doThrow(new RuntimeException("Dummy exception message."))
        .when(jobsClientMock)
        .getTasks(new JobInstance("test-jobName", "jobId-1"));

    Task task = new Task("notLocalHost", "test-task", "0", new ArrayList<>(), ImmutableList.of("test-store"));

    Mockito.when(jobsClientMock.getTasks(new JobInstance("test-jobName", "jobId"))).thenReturn(ImmutableList.of(task));

    Map<String, String> configMap = new HashMap<>(config);
    configMap.put(LocalStoreMonitorConfig.CONFIG_IGNORE_FAILURES, "true");

    LocalStoreMonitor localStoreMonitor =
        new LocalStoreMonitor(new LocalStoreMonitorConfig(new MapConfig(configMap)), localStoreMonitorMetrics,
            jobsClientMock);

    localStoreMonitor.monitor();

    // Non failing job directory should be cleaned up.
    assertTrue("Task store directory should not exist.", !taskStoreDir.exists());
    FileUtils.deleteDirectory(testFailingJobDir);
  }

}