class DummyClass_175753 {
  @Test
  public void testRefreshProjectsForSelectedCredential()
      throws ProjectRepositoryException, InterruptedException {
    when(loginService.getAccounts()).thenReturn(twoAccountSet);
    initializeProjectRepository();

    deployPanel = createPanel(false /* requireValues */);
    Table projectTable = getProjectSelector().getViewer().getTable();
    assertNull(deployPanel.latestGcpProjectQueryJob);
    assertThat(projectTable.getItemCount(), is(0));

    selectAccount(account1);
    assertNotNull(deployPanel.latestGcpProjectQueryJob);
    deployPanel.latestGcpProjectQueryJob.join();
    assertThat(projectTable.getItemCount(), is(2));
    assertThat(((GcpProject) projectTable.getItem(0).getData()).getId(), is("projectId1"));
    assertThat(((GcpProject) projectTable.getItem(1).getData()).getId(), is("projectId2"));
  }

}