class DummyClass_175747 {
  @Test
  public void testValidationMessageWhenSignedIn() {
    // Return two accounts because the account selector will auto-select if there exists only one.
    when(loginService.getAccounts()).thenReturn(twoAccountSet);

    deployPanel = createPanel(true /* requireValues */);
    IStatus status = getAccountSelectorValidationStatus();
    assertThat(status.getMessage(), is("Select an account."));
  }

}