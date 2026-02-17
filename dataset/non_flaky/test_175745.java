class DummyClass_175745 {
  @Test
  public void testAutoSelectSingleAccount_loadGcpProjects()
      throws ProjectRepositoryException, InterruptedException {
    when(loginService.getAccounts()).thenReturn(oneAccountSet);
    initializeProjectRepository();
    deployPanel = createPanel(true /* requireValues */);
    assertNotNull(deployPanel.latestGcpProjectQueryJob);
    deployPanel.latestGcpProjectQueryJob.join();

    Table projectTable = getProjectSelector().getViewer().getTable();
    assertThat(projectTable.getItemCount(), is(2));
  }

}