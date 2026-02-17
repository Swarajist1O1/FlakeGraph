class DummyClass_175744 {
  @Test
  public void testAutoSelectSingleAccount() {
    when(loginService.getAccounts()).thenReturn(oneAccountSet);
    deployPanel = createPanel(true /* requireValues */);
    assertThat(deployPanel.getSelectedCredential(), is(credential));

    // verify not in error
    IStatus status = getAccountSelectorValidationStatus();
    assertTrue("account selector is in error: " + status.getMessage(), status.isOK());

    assertThat("auto-selected value should be propagated back to model",
        deployPanel.model.getAccountEmail(), is(account1.getEmail()));
  }

}