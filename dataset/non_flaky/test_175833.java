class DummyClass_175833 {
  @Test
  public void testValidation_tooLongNameWithoutDot() {
    assertThat(validator.validate(LENGTH_63 + "4").getSeverity(), is(IStatus.ERROR));
  }

}