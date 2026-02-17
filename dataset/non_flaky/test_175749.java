class DummyClass_175749 {
  @Test
  public void testProjectSavedInPreferencesSelected()
      throws ProjectRepositoryException, InterruptedException, BackingStoreException {
    IEclipsePreferences node =
        new ProjectScope(project).getNode(DeployPreferences.PREFERENCE_STORE_QUALIFIER);
    try {
      node.put("project.id", "projectId1");
      node.put("account.email", EMAIL_1);
      initializeProjectRepository();
      when(loginService.getAccounts()).thenReturn(twoAccountSet);
      deployPanel = createPanel(true /* requireValues */);
      deployPanel.latestGcpProjectQueryJob.join();

      ProjectSelector projectSelector = getProjectSelector();
      IStructuredSelection selection = projectSelector.getViewer().getStructuredSelection();
      assertThat(selection.size(), is(1));
      assertThat(((GcpProject) selection.getFirstElement()).getId(), is("projectId1"));
    } finally {
      node.clear();
    }
  }

}