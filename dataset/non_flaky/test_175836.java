class DummyClass_175836 {
  @Test
  public void testValidation_maxLengthWithDot() {
    assertThat(validator.validate(LENGTH_222).getSeverity(), is(IStatus.OK));
  }

}