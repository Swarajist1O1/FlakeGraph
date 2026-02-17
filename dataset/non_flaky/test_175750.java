class DummyClass_175750 {
  @Test
  public void testProjectNotSelectedIsAnErrorWhenRequireValuesIsTrue() {
    deployPanel = createPanel(true /* requireValues */);
    assertThat(getProjectSelectionValidator().getSeverity(), is(IStatus.ERROR));
  }

}