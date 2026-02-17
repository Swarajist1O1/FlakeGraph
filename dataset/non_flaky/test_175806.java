class DummyClass_175806 {
  @Test
  public void testSelectionChanged_changeSelectedProject()
      throws ProjectRepositoryException, InterruptedException {
    when(projectRepository.getAppEngineApplication(any(Credential.class), eq("oldProjectId")))
        .thenThrow(new ProjectRepositoryException("testException"));
    when(projectRepository.getAppEngineApplication(any(Credential.class), eq("projectId")))
        .thenReturn(AppEngine.NO_APPENGINE_APPLICATION);

    initSelectionAndAccountSelector(new GcpProject("oldProjectName", "oldProjectId"));
    listener.selectionChanged(event);

    Job oldJob = listener.latestQueryJob;
    assertNotNull(oldJob);
    oldJob.join();

    initSelectionAndAccountSelector();
    listener.selectionChanged(event);

    Job newJob = listener.latestQueryJob;
    assertNotNull(newJob);
    assertNotEquals(oldJob, newJob);
    newJob.join();

    verify(projectRepository).getAppEngineApplication(any(Credential.class), eq("oldProjectId"));
    verify(projectRepository).getAppEngineApplication(any(Credential.class), eq("projectId"));
    verify(projectSelector).setStatusLink(EXPECTED_MESSAGE_WHEN_NO_APPLICATION, EXPECTED_LINK);
  }

}