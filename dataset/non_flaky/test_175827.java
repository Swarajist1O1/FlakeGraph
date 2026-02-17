class DummyClass_175827 {
  @Test
  public void testValidation_endWithDot() {
    assertThat(validator.validate("bucket.").getSeverity(), is(IStatus.ERROR));
  }

}