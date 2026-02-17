class DummyClass_175802 {
  @Test
  public void testSelectionChanged_noAppEngineApplication()
      throws ProjectRepositoryException, InterruptedException {
    initSelectionAndAccountSelector();
    when(projectRepository.getAppEngineApplication(any(Credential.class), anyString()))
        .thenReturn(AppEngine.NO_APPENGINE_APPLICATION);

    listener.selectionChanged(event);
    listener.latestQueryJob.join();
    verify(projectSelector).clearStatusLink();  // Should clear initially.
    verify(projectSelector).setStatusLink(EXPECTED_MESSAGE_WHEN_NO_APPLICATION, EXPECTED_LINK);
  }

}