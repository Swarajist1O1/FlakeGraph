class DummyClass_175761 {
  @Test
  public void testRun_abandonStaleJob() throws InterruptedException, ProjectRepositoryException {
    // Prepare another concurrent query job.
    Credential staleCredential = mock(Credential.class);

    List<GcpProject> anotherProjectList = mock(List.class);
    ProjectRepository projectRepository2 = mock(ProjectRepository.class);
    when(projectRepository2.getProjects(staleCredential)).thenReturn(anotherProjectList);

    Predicate<Job> notLatest = mock(Predicate.class);
    Job staleJob = new GcpProjectQueryJob(staleCredential, projectRepository2,
        projectSelector, dataBindingContext, notLatest);

    // This second job is stale, i.e., it was fired, but user has selected another credential.
    when(notLatest.apply(staleJob)).thenReturn(false);

    queryJob.schedule();
    queryJob.join();
    // Make the stale job complete even after "queryJob" finishes.
    staleJob.schedule();
    staleJob.join();

    verify(projectRepository).getProjects(credential);
    verify(projectRepository2).getProjects(staleCredential);

    verify(projectSelector).setProjects(projects);
    verify(projectSelector, never()).setProjects(anotherProjectList);
  }

}