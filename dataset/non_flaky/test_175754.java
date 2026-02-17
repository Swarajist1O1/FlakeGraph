class DummyClass_175754 {
  @Test
  public void testRefreshProjectsForSelectedCredential_switchAccounts()
      throws ProjectRepositoryException, InterruptedException {
    when(loginService.getAccounts()).thenReturn(twoAccountSet);
    initializeProjectRepository();

    deployPanel = createPanel(false /* requireValues */);
    Table projectTable = getProjectSelector().getViewer().getTable();
    assertNull(deployPanel.latestGcpProjectQueryJob);
    assertThat(projectTable.getItemCount(), is(0));

    selectAccount(account1);
    Job jobForAccount1 = deployPanel.latestGcpProjectQueryJob;
    jobForAccount1.join();
    assertThat(projectTable.getItemCount(), is(2));

    selectAccount(account2);
    assertNotEquals(jobForAccount1, deployPanel.latestGcpProjectQueryJob);
    deployPanel.latestGcpProjectQueryJob.join();
    assertThat(projectTable.getItemCount(), is(1));
    assertThat(((GcpProject) projectTable.getItem(0).getData()).getId(), is("projectId2"));
  }

}