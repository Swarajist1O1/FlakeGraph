class DummyClass_175791 {
  @Test
  public void testRun_abandonIfNotLatestJob()
      throws InterruptedException, ProjectRepositoryException {
    when(isLatestQueryJob.apply(queryJob)).thenReturn(false);
    when(projectRepository.getAppEngineApplication(credential, "projectId"))
        .thenReturn(AppEngine.NO_APPENGINE_APPLICATION);

    queryJob.schedule();
    queryJob.join();

    verify(isLatestQueryJob).apply(queryJob);
    verify(projectSelector, never()).setStatusLink(anyString(), anyString());
  }

}