class DummyClass_175828 {
  @Test
  public void testValidation_startWithHyphen() {
    assertThat(validator.validate("-bucket").getSeverity(), is(IStatus.ERROR));
  }

}