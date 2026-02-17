class DummyClass_175835 {
  @Test
  public void testValidation_tooLongNameWithDot() {
    assertThat(validator.validate(LENGTH_222 + "9").getSeverity(), is(IStatus.ERROR));
  }

}