class DummyClass_175826 {
  @Test
  public void testValidation_startWithDot() {
    assertThat(validator.validate(".bucket").getSeverity(), is(IStatus.ERROR));
  }

}