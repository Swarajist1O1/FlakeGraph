class DummyClass_96012 {
  @Test
  public void testCoref() throws IOException {
    String doc = IOUtils.slurpFile("edu/stanford/nlp/dcoref/STILLALONEWOLF_20050102.1100.eng.LDC2005E83.sgm");
    Annotation annotation = pipeline.process(doc);
    Map<Integer, CorefChain> chains = annotation.get(CorefCoreAnnotations.CorefChainAnnotation.class);
    Map<Integer, List<ExpectedMention>> expected = loadExpectedResults("edu/stanford/nlp/dcoref/STILLALONEWOLF_20050102.1100.eng.LDC2005E83.expectedcoref");
    compareResults(expected, chains);
  }

}