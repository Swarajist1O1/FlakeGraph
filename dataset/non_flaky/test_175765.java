class DummyClass_175765 {
  @Test
  public void testGetHelpContextId() {
    FlexDeployPreferencesPanel panel = createPanel(true /* requireValues */);

    assertEquals(
        "com.google.cloud.tools.eclipse.appengine.deploy.ui.DeployAppEngineFlexProjectContext",
        panel.getHelpContextId());
  }

}