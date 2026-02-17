class DummyClass_175825 {
  @Test
  public void testValidation_upperCaseLetter() {
    IStatus status = validator.validate("THISWOULDBEVALIDIFLOWERCASE");
    assertThat(status.getSeverity(), is(IStatus.ERROR));
    assertThat(status.getMessage(), is("Invalid bucket name: THISWOULDBEVALIDIFLOWERCASE"));
  }

}