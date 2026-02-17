class DummyClass_175830 {
  @Test
  public void testValidation_startWithUnderscore() {
    assertThat(validator.validate("_bucket").getSeverity(), is(IStatus.ERROR));
  }

}