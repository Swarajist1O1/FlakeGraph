class DummyClass_96079 {
  @Test
  public void buildFrenchPipeline() {
    // expected output
    List<String> expectedTokens = Arrays.asList("Emmanuel", "Macron", "est", "le", "prÃ©sident", "de", "la", "France", ".");
    List<String> expectedTags = Arrays.asList("PROPN", "PROPN", "AUX", "DET", "NOUN", "ADP", "DET", "PROPN", "PUNCT");
    List<String> expectedNER = Arrays.asList("I-PER", "I-PER", "O", "O", "O", "O", "I-LOC", "I-LOC", "O");
    String expectedDependencyParse = "root(ROOT-0, prÃ©sident-5)\n" +
        "nsubj(prÃ©sident-5, Emmanuel-1)\n" +
        "flat:name(Emmanuel-1, Macron-2)\n" +
        "cop(prÃ©sident-5, est-3)\n" +
        "det(prÃ©sident-5, le-4)\n" +
        "case(France-8, de-6)\n" +
        "det(France-8, la-7)\n" +
        "nmod:de(prÃ©sident-5, France-8)\n" +
        "punct(prÃ©sident-5, .-9)\n";
    // build doc
    CoreDocument doc = new CoreDocument("Emmanuel Macron est le prÃ©sident de la France.");
    // build pipeline with language name
    StanfordCoreNLP frenchPipeline = new StanfordCoreNLP("french");
    // annotate
    frenchPipeline.annotate(doc);
    // compare results
    assertEquals(expectedTokens, doc.tokens().stream().map(w -> w.word()).collect(Collectors.toList()));
    assertEquals(expectedTags, doc.tokens().stream().map(w -> w.tag()).collect(Collectors.toList()));
    assertEquals(expectedNER, doc.tokens().stream().map(w -> w.ner()).collect(Collectors.toList()));
    assertEquals(expectedDependencyParse, doc.sentences().get(0).dependencyParse().toList());
  }

}