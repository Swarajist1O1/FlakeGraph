class DummyClass_175792 {
  @Test
  public void testRun_abandonIfProjectSelectorHasNoSelection()
      throws ProjectRepositoryException, InterruptedException {
    when(projectRepository.getAppEngineApplication(credential, "projectId"))
        .thenReturn(AppEngine.NO_APPENGINE_APPLICATION);
    when(projectSelection.isEmpty()).thenReturn(true);

    queryJob.schedule();
    queryJob.join();

    verify(isLatestQueryJob).apply(queryJob);
    verify(projectSelector, never()).setStatusLink(anyString(), anyString());
  }

}