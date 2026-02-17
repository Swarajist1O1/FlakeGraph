class DummyClass_175766 {
  @Test
  public void testDefaultAppYamlPathSet() {
    FlexDeployPreferencesPanel panel = createPanel(true /* requireValues */);

    Text appYamlField = findAppYamlField(panel);
    assertEquals("src/main/appengine/app.yaml", appYamlField.getText());
    assertTrue(getAppYamlPathValidationStatus(panel).isOK());
  }

}