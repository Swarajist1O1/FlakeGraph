class DummyClass_175769 {
  @Test
  public void testAppYamlPathValidation_absolutePathWorks() {
    FlexDeployPreferencesPanel panel = createPanel(true /* requireValues */);
    Text appYamlField = findAppYamlField(panel);

    IPath absolutePath = project.getLocation().append("src/main/appengine/app.yaml");
    assertTrue(absolutePath.isAbsolute());

    appYamlField.setText(absolutePath.toString());
    assertTrue(getAppYamlPathValidationStatus(panel).isOK());
  }

}