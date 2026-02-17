class DummyClass_175834 {
  @Test
  public void testValidation_validNameWithDot() {
    assertThat(validator.validate(LENGTH_64_WITH_DOT).getSeverity(), is(IStatus.OK));
  }

}