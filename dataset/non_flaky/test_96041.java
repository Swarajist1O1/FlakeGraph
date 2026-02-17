class DummyClass_96041 {
  @Test
  public void testTokenSequenceMatchesWildcard() throws IOException {
    CoreMap doc = createDocument("word1 word2");

    // Test sequence with groups
    TokenSequencePattern p = TokenSequencePattern.compile( "[]{2}|[]");
    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    boolean matches = m.matches();
    assertTrue(matches);

    // Reverse order
    p = TokenSequencePattern.compile( "[]|[]{2}");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    matches = m.matches();
    assertTrue(matches);

    // Using {1,2}
    p = TokenSequencePattern.compile( "[]{1,2}");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    matches = m.matches();
    assertTrue(matches);
  }

}