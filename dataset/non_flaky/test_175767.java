class DummyClass_175767 {
  @Test
  public void testAppYamlPathValidation_nonExistingAppYaml() {
    FlexDeployPreferencesPanel panel = createPanel(true /* requireValues */);

    Text appYamlField = findAppYamlField(panel);
    appYamlField.setText("non/existing/app.yaml");
    assertFalse(getAppYamlPathValidationStatus(panel).isOK());
  }

}