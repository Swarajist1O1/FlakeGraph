class DummyClass_175788 {
  @Test
  public void testRun_projectHasApplication()
      throws ProjectRepositoryException, InterruptedException {
    AppEngine appEngine = AppEngine.withId("unique-id");
    when(projectRepository.getAppEngineApplication(credential, "projectId")).thenReturn(appEngine);

    queryJob.schedule();
    queryJob.join();

    verify(isLatestQueryJob, never()).apply(queryJob);
    verify(projectSelector, never()).isDisposed();
    verify(projectSelector, never()).setStatusLink(anyString(), anyString());

    assertTrue(appEngine == project.getAppEngine());
  }

}