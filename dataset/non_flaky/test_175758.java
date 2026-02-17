class DummyClass_175758 {
  @Test
  public void testRun_setsProjects() throws InterruptedException, ProjectRepositoryException {
    queryJob.schedule();
    queryJob.join();

    verify(projectRepository).getProjects(credential);
    verify(isLatestQueryJob).apply(queryJob);
    verify(projectSelector).isDisposed();
    verify(projectSelector).setProjects(projects);
  }

}