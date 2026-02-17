class DummyClass_175768 {
  @Test
  public void testAppYamlPathValidation_noValidationIfRequireValuesIsFalse() {
    FlexDeployPreferencesPanel panel = createPanel(false /* requireValues */);

    Text appYamlField = findAppYamlField(panel);
    appYamlField.setText("non/existing/app.yaml");
    assertNull(getAppYamlPathValidationStatus(panel));
  }

}