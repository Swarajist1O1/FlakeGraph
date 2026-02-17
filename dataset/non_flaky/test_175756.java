class DummyClass_175756 {
  @Test
  public void testLimitedVisibility() {
    NodeList pages = getDocument().getElementsByTagName("page");
    Assert.assertEquals(2, pages.getLength());
    NodeList enabledWhen = getDocument().getElementsByTagName("enabledWhen");
    Assert.assertEquals(4, enabledWhen.getLength());
    NodeList tests = getDocument().getElementsByTagName("test");
    Assert.assertEquals(4, tests.getLength());
    NodeList adapts = getDocument().getElementsByTagName("adapt");
    Assert.assertEquals(4, adapts.getLength());

    for (int i = 0; i < enabledWhen.getLength(); i++) {
      Element element = (Element) enabledWhen.item(i);
      Node parent = element.getParentNode();
      assertThat(parent.getNodeName(), either(is("page")).or(is("handler")));
    }

    Element standardAdapt = (Element) adapts.item(0);
    verifyAdapt(standardAdapt, AppEngineStandardFacet.ID);
    Element flexAdapt = (Element) adapts.item(1);
    verifyAdapt(flexAdapt, AppEngineFlexFacet.ID);
  }

}