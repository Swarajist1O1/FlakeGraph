class DummyClass_175746 {
  @Test
  public void testValidationMessageWhenNotSignedIn() {
    deployPanel = createPanel(true /* requireValues */);
    IStatus status = getAccountSelectorValidationStatus();
    assertThat(status.getMessage(), is("Sign in to Google."));
  }

}