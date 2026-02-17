class DummyClass_96097 {
  @Test
  public void testQuote() {
    testAnnotators("quote");
    testAnnotators("tokenize,quote");
    testAnnotators("tokenize,ssplit,quote");
    testAnnotators("tokenize,ssplit,quote");
    testAnnotators("tokenize,ssplit,pos,lemma,ner,depparse,coref,quote");
  }

}