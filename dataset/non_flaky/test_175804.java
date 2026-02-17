class DummyClass_175804 {
  @Test
  public void testSelectionChanged_doNotRunQueryJobIfCached() throws ProjectRepositoryException {
    GcpProject gcpProject = new GcpProject("projectName", "projectId");
    initSelectionAndAccountSelector(gcpProject);
    gcpProject.setAppEngine(AppEngine.withId("id"));

    listener.selectionChanged(event);
    assertNull(listener.latestQueryJob);
    verify(projectRepository, never()).getAppEngineApplication(any(Credential.class), anyString());
    verify(projectSelector).clearStatusLink();
  }

}