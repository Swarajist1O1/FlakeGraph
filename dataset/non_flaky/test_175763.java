class DummyClass_175763 {
  @Test
  public void testCorrectPanelIsShownForFacetedProject() {
    DeployPropertyPage page = new DeployPropertyPage(loginService, googleApiFactory);
    Shell parent = shellTestResource.getShell();
    page.setElement(getProject());
    page.createControl(parent);
    page.setVisible(true);
    Composite preferencePageComposite = (Composite) parent.getChildren()[0];
    for (Control control : preferencePageComposite.getChildren()) {
      if (control instanceof Composite) {
        Composite maybeDeployPageComposite = (Composite) control;
        Layout layout = maybeDeployPageComposite.getLayout();
        if (layout instanceof StackLayout) {
          StackLayout stackLayout = (StackLayout) layout;
          assertThat(stackLayout.topControl, instanceOf(getPanelClass()));
          return;
        }
      }
    }
    fail("Did not find the deploy preferences panel");
  }

}