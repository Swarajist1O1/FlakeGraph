class DummyClass_96039 {
  @Test
  public void testTokenSequenceMatcherAAs() throws IOException {
    StringBuilder s = new StringBuilder();
 //   Timing timing = new Timing();
    for (int i = 1; i <= 10; i++) {
      s.append("A ");
      CoreMap doc = createDocument(s.toString());
      TokenSequencePattern p = TokenSequencePattern.compile("(A?)" + "{" + i + "} " + "A" + "{" + i + "}");
//      TokenSequencePattern p = TokenSequencePattern.compile( "(A?)" + "{" + i + "}");
      TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
//      timing.start();
      boolean match = m.matches();
      assertTrue(match);
//      timing.stop("matched: " + match + " " + i);
    }
  }

}