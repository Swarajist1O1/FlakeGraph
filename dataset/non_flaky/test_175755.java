class DummyClass_175755 {
  @Test
  public void testNoProjectSelectedWhenSwitchingAccounts()
      throws ProjectRepositoryException, InterruptedException {
    when(loginService.getAccounts()).thenReturn(twoAccountSet);
    initializeProjectRepository();

    deployPanel = createPanel(false /* requireValues */);
    selectAccount(account1);
    deployPanel.latestGcpProjectQueryJob.join();

    Table projectTable = getProjectSelector().getViewer().getTable();
    assertThat(projectTable.getItemCount(), is(2));
    projectTable.setSelection(0);
    assertThat(projectTable.getSelectionCount(), is(1));

    selectAccount(account2);
    deployPanel.latestGcpProjectQueryJob.join();

    assertThat(projectTable.getItemCount(), is(1));
    assertThat(projectTable.getSelectionCount(), is(0));
  }

}