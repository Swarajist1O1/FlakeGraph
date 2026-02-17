class DummyClass_175823 {
  @Test
  public void testValidation_nonStringInput() {
    IStatus status = validator.validate(new Object());
    assertThat(status.getSeverity(), is(IStatus.ERROR));
    assertThat(status.getMessage(), is("Invalid bucket name"));
  }

}