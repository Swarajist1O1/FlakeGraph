class DummyClass_175805 {
  @Test
  public void testSelectionChanged_whenCachedResultIsNoAppEngineApplication()
      throws ProjectRepositoryException {
    GcpProject gcpProject = new GcpProject("projectName", "projectId");
    initSelectionAndAccountSelector(gcpProject);
    gcpProject.setAppEngine(AppEngine.NO_APPENGINE_APPLICATION);

    listener.selectionChanged(event);
    assertNull(listener.latestQueryJob);
    verify(projectRepository, never()).getAppEngineApplication(any(Credential.class), anyString());
    verify(projectSelector).setStatusLink(EXPECTED_MESSAGE_WHEN_NO_APPLICATION, EXPECTED_LINK);
  }

}