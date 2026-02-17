class DummyClass_96040 {
  @Test
  public void testTokenSequenceFindsWildcard() throws IOException {
    CoreMap doc = createDocument("word1 word2");

    // Test sequence with groups
    TokenSequencePattern p = TokenSequencePattern.compile( "[]{2}|[]");
    TokenSequenceMatcher m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    boolean match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("word1 word2", m.group());
    match = m.find();
    assertFalse(match);

    // Reverse order
    p = TokenSequencePattern.compile( "[]|[]{2}");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("word1 word2", m.group());
    match = m.find();
    assertFalse(match);

    // Using {1,2}
    p = TokenSequencePattern.compile( "[]{2}");
    m = p.getMatcher(doc.get(CoreAnnotations.TokensAnnotation.class));
    match = m.find();
    assertTrue(match);
    assertEquals(0, m.groupCount());
    assertEquals("word1 word2", m.group());
    match = m.find();
    assertFalse(match);
  }

}