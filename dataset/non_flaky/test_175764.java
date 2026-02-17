class DummyClass_175764 {
  @Test
  public void testGetHelpContextId() {
    IProject project = mock(IProject.class);
    when(project.getName()).thenReturn("");
    StandardDeployPreferencesPanel panel = new StandardDeployPreferencesPanel(
        shellResource.getShell(), project, mock(IGoogleLoginService.class), mock(Runnable.class),
        false, mock(ProjectRepository.class));

    assertEquals(
        "com.google.cloud.tools.eclipse.appengine.deploy.ui.DeployAppEngineStandardProjectContext",
        panel.getHelpContextId());
  }

}