class DummyClass_175770 {
  @Test
  public void testFlexPricingLabel() {
    dialog.setBlockOnOpen(false);
    dialog.open();
    Composite dialogArea = (Composite) dialog.createDialogArea(shellResource.getShell());

    assertNotNull(findGcpPricingLink(dialogArea));
  }

}