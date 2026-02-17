class DummyClass_175759 {
  @Test
  public void testRun_abandonIfDisposed() throws InterruptedException, ProjectRepositoryException {
    when(projectSelector.isDisposed()).thenReturn(true);

    queryJob.schedule();
    queryJob.join();

    verify(projectRepository).getProjects(credential);
    verify(projectSelector, never()).setProjects(projects);
  }

}