class DummyClass_175752 {
  @Test
  public void testProjectsExistThenNoProjectNotFoundError()
      throws ProjectRepositoryException, InterruptedException {
    when(loginService.getAccounts()).thenReturn(oneAccountSet);
    initializeProjectRepository();
    deployPanel = createPanel(false /* requireValues */);
    selectAccount(account1);
    deployPanel.latestGcpProjectQueryJob.join();
    assertThat(getProjectSelectionValidator().getSeverity(), is(IStatus.OK));
  }

}