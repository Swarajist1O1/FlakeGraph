class DummyClass_175751 {
  @Test
  public void testProjectNotSelectedIsNotAnErrorWhenRequireValuesIsFalse() {
    deployPanel = createPanel(false /* requireValues */);
    assertThat(getProjectSelectionValidator().getSeverity(), is(IStatus.INFO));
  }

}