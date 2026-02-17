class DummyClass_175831 {
  @Test
  public void testValidation_endWithUnderscore() {
    assertThat(validator.validate("bucket_").getSeverity(), is(IStatus.ERROR));
  }

}