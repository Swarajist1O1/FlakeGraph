class DummyClass_175829 {
  @Test
  public void testValidation_endWithHyphen() {
    assertThat(validator.validate("bucket-").getSeverity(), is(IStatus.ERROR));
  }

}