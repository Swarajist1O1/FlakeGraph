class DummyClass_175790 {
  @Test
  public void testRun_abandonIfDisposed() throws InterruptedException, ProjectRepositoryException {
    when(projectSelector.isDisposed()).thenReturn(true);
    when(projectRepository.getAppEngineApplication(credential, "projectId"))
        .thenReturn(AppEngine.NO_APPENGINE_APPLICATION);

    queryJob.schedule();
    queryJob.join();

    verify(projectSelector).isDisposed();
    verify(projectSelector, never()).setStatusLink(anyString(), anyString());
  }

}