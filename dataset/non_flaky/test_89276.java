class DummyClass_89276 {
  @Test
  public void testGetJobStatuses() throws IOException, InterruptedException {
    doReturn(APPS_RESPONSE.getBytes()).when(provider).httpGet(anyString());

    List<Job> jobs = Lists.newArrayList(
        new Job("job1", "1"),  // Job with multiple applications, 1 RUNNING
        new Job("job2", "1"),  // Job with 1 KILLED application
        new Job("job3", "1"),  // Job with 1 RUNNING application
        new Job("job4", "1")); // Job not found in YARN
    provider.getJobStatuses(jobs);

    Collections.sort(jobs, (o1, o2) -> o1.getJobName().compareTo(o2.getJobName()));

    assertEquals(4, jobs.size());
    verifyJobStatus(jobs.get(0), "job1", JobStatus.STARTED, "RUNNING");
    verifyJobStatus(jobs.get(1), "job2", JobStatus.STOPPED, "KILLED");
    verifyJobStatus(jobs.get(2), "job3", JobStatus.STARTED, "RUNNING");
    verifyJobStatus(jobs.get(3), "job4", JobStatus.UNKNOWN, null);
  }

}