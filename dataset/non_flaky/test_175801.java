class DummyClass_175801 {
  @Test
  public void testSelectionChanged_repositoryException()
      throws ProjectRepositoryException, InterruptedException {
    initSelectionAndAccountSelector();
    when(projectRepository.getAppEngineApplication(any(Credential.class), anyString()))
        .thenThrow(new ProjectRepositoryException("testException"));

    listener.selectionChanged(event);
    listener.latestQueryJob.join();
    verify(projectSelector).clearStatusLink();  // Should clear initially.
    verify(projectSelector).setStatusLink(EXPECTED_MESSAGE_WHEN_EXCEPTION, null /* tooltip */);
  }

}