class DummyClass_96014 {
  @Test
  public void testRebuildingMWTText() throws IOException {
    // set up French properties
    Properties frenchProperties = LanguageInfo.getLanguageProperties("french");
    frenchProperties.setProperty("annotators", "tokenize,ssplit,mwt");
    StanfordCoreNLP frenchPipeline = new StanfordCoreNLP(frenchProperties);
    String frenchText = "Le but des bandes de roulement est d'augmenter la traction.";
    CoreDocument frenchDoc = new CoreDocument(frenchPipeline.process(frenchText));
    String rebuiltFrenchText = SentenceUtils.listToOriginalTextString(frenchDoc.tokens());
    assertTrue(frenchText.equals(rebuiltFrenchText));
  }

}