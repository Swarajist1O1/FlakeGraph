class DummyClass_156099 {
  @Test
  public void testElemApplyTemplates() {
    final String clazz = "org.apache.xalan.templates.ElemApplyTemplates";
    final String[] params = { "org.apache.xalan.transformer.TransformerImpl" };
    runXalanTest(prepareTarget(methodSigFromComponents(clazz, "void", "transformSelectedNodes", params), clazz));
  }

}