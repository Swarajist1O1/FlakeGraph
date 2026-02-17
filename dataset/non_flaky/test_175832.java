class DummyClass_175832 {
  @Test
  public void testValidation_maxLengthWithoutDot() {
    assertThat(validator.validate(LENGTH_63).getSeverity(), is(IStatus.OK));
  }

}