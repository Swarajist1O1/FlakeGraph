class DummyClass_96022 {
  @Test
  public void testTokenSequenceMatcherConj2() throws IOException {
    String content = "The cat is sleeping on the floor.";
    String greedyPattern = "(?: ([]* cat []*) & ([]* sleeping []*))";

    TokenizerFactory tf = PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
    List<CoreLabel> tokens = tf.getTokenizer(new StringReader(content)).tokenize();
    TokenSequencePattern seqPattern = TokenSequencePattern.compile(greedyPattern);
    TokenSequenceMatcher matcher = seqPattern.getMatcher(tokens);

    boolean entireMatch = matcher.matches();
    assertTrue(entireMatch);

    boolean match = matcher.find();
    assertTrue(match);
    assertEquals("The cat is sleeping on the floor.", matcher.group());

    String reluctantPattern = "(?: ([]*? cat []*?) & ([]*? sleeping []*?))";
    TokenSequencePattern seqPattern2 = TokenSequencePattern.compile(reluctantPattern);
    TokenSequenceMatcher matcher2 = seqPattern2.getMatcher(tokens);

    match = matcher2.find();
    assertTrue(match);
    assertEquals("The cat is sleeping", matcher2.group());
  }

}