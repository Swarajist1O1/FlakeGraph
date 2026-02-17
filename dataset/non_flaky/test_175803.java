class DummyClass_175803 {
  @Test
  public void testSelectionChanged_hasAppEngineApplication()
      throws ProjectRepositoryException, InterruptedException {
    initSelectionAndAccountSelector();
    when(projectRepository.getAppEngineApplication(any(Credential.class), anyString()))
        .thenReturn(AppEngine.withId("id"));

    listener.selectionChanged(event);
    listener.latestQueryJob.join();
    verify(projectSelector).clearStatusLink();
  }

}