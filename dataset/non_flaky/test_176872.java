class DummyClass_176872 {
  @Test
  public void testToString() throws Exception {
    PMML model = buildDummyModel();
    model.getHeader().setTimestamp(null);
    assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                 "<PMML version=\"4.2.1\" xmlns=\"http://www.dmg.org/PMML-4_2\">\n" +
                 "    <Header>\n" +
                 "        <Application name=\"Oryx\"/>\n" +
                 "    </Header>\n" +
                 "    <TreeModel functionName=\"classification\">\n" +
                 "        <Node recordCount=\"123.0\"/>\n" +
                 "    </TreeModel>\n" +
                 "</PMML>\n",
                 PMMLUtils.toString(model));
  }

}