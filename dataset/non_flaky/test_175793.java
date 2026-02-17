class DummyClass_175793 {
  @Test
  public void testRun_abandonStaleJob() throws InterruptedException, ProjectRepositoryException {
    when(projectRepository.getAppEngineApplication(credential, "projectId"))
        .thenReturn(AppEngine.NO_APPENGINE_APPLICATION);

    // Prepare another concurrent query job.
    Credential staleCredential = mock(Credential.class);

    GcpProject staleProject = new GcpProject("name", "staleProjectId");
    ProjectRepository projectRepository2 = mock(ProjectRepository.class);
    when(projectRepository2.getAppEngineApplication(staleCredential, "staleProjectId"))
        .thenThrow(new ProjectRepositoryException("testException"));

    Predicate<Job> notLatest = mock(Predicate.class);
    Job staleJob = new AppEngineApplicationQueryJob(staleProject, staleCredential,
        projectRepository2, projectSelector, EXPECTED_LINK, notLatest);

    // This second job is stale, i.e., it was fired, but user has selected another credential.
    when(notLatest.apply(staleJob)).thenReturn(false);

    queryJob.schedule();
    queryJob.join();
    // Make the stale job complete even after "queryJob" finishes.
    staleJob.schedule();
    staleJob.join();

    verify(projectRepository).getAppEngineApplication(credential, "projectId");
    verify(projectRepository2).getAppEngineApplication(staleCredential, "staleProjectId");

    verify(projectSelector).setStatusLink(EXPECTED_MESSAGE_WHEN_NO_APPLICATION, EXPECTED_LINK);
    verify(projectSelector, never()).setStatusLink(EXPECTED_MESSAGE_WHEN_EXCEPTION, null);
  }

}