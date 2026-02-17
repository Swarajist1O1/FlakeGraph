class DummyClass_175811 {
  @Test
  public void testExtensionPoint() {
    NodeList extensions = getDocument().getElementsByTagName("extension");
    assertEquals(1, extensions.getLength());
    Element extension = (Element) extensions.item(0);
    assertEquals("org.eclipse.ui.commands", extension.getAttribute("point"));

    NodeList commandDefinitions = extension.getElementsByTagName("command");
    assertEquals(1, commandDefinitions.getLength());
    Element configExtension = (Element) commandDefinitions.item(0);
    assertEquals(OpenDropDownMenuHandler.class.getName(),
        configExtension.getAttribute("defaultHandler"));
    assertEquals("com.google.cloud.tools.eclipse.ui.util.showPopup",
        configExtension.getAttribute("id"));
  }

}