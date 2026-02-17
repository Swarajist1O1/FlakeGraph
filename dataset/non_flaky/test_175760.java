class DummyClass_175760 {
  @Test
  public void testRun_abandonIfNotLatestJob()
      throws InterruptedException, ProjectRepositoryException {
    when(isLatestQueryJob.apply(queryJob)).thenReturn(false);

    queryJob.schedule();
    queryJob.join();

    verify(projectRepository).getProjects(credential);
    verify(projectSelector, never()).setProjects(projects);
  }

}